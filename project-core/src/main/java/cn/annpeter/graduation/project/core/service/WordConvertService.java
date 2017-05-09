package cn.annpeter.graduation.project.core.service;

import cn.annpeter.graduation.project.base.common.exception.CommonException;
import cn.annpeter.graduation.project.base.common.model.ResultCodeEnum;
import cn.annpeter.graduation.project.base.common.util.EncryptUtils;
import cn.annpeter.graduation.project.core.service.helper.CacheTemplateService;
import org.apache.poi.xslf.usermodel.*;
import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static cn.annpeter.graduation.project.core.config.GlobalConfig.web;

/**
 * Created on 2017/05/01
 *
 * @author annpeter.it@gmail.com
 */
@Service
public class WordConvertService {

    @Resource
    private CacheTemplateService cacheTemplateService;

    public String docxToHtml(String sourceFileName) {

        String key = EncryptUtils.MD5(sourceFileName);

        return (String) cacheTemplateService.findCache(key, 1, TimeUnit.DAYS, () -> {
            OutputStreamWriter outputStreamWriter;
            String imgPath = "/docxImg/" + key;

            try (InputStream inputStream = (new URL(sourceFileName).openConnection()).getInputStream();
                 OutputStream htmlOutputStream = new ByteArrayOutputStream()) {

                XWPFDocument document = new XWPFDocument(inputStream);
                XHTMLOptions options = XHTMLOptions.create();
                // 存放图片的文件夹
                options.setExtractor(new FileImageExtractor(new File(web.fileUploadBaseDir + imgPath)));
                // html中图片的路径
                options.URIResolver(new BasicURIResolver("/fileUpload" + imgPath));
                outputStreamWriter = new OutputStreamWriter(htmlOutputStream, "utf-8");
                XHTMLConverter xhtmlConverter = (XHTMLConverter) XHTMLConverter.getInstance();
                xhtmlConverter.convert(document, outputStreamWriter, options);

                return htmlOutputStream.toString();
            } catch (Exception ex) {
                throw new CommonException(ResultCodeEnum.UNKNOWN_ERROR, "word转html未知错误", ex);
            }
        });
    }


    public List<String> pptToImage(String sourceFileName) {
        String key = EncryptUtils.MD5(sourceFileName);
        String imgPath = "/docxImg/" + key;

        List<String> outList = new LinkedList<>();
        try (InputStream inputStream = (new URL(sourceFileName).openConnection()).getInputStream()) {
            XMLSlideShow ppt = new XMLSlideShow(inputStream);
            Dimension pageSize = ppt.getPageSize();

            for (int i = 0; i < ppt.getSlides().size(); i++) {
                // 防止中文乱码
                for (XSLFShape shape : ppt.getSlides().get(i).getShapes()) {
                    if (shape instanceof XSLFTextShape) {
                        XSLFTextShape tsh = (XSLFTextShape) shape;
                        for (XSLFTextParagraph p : tsh) {
                            for (XSLFTextRun r : p) {
                                r.setFontFamily("宋体");
                            }
                        }
                    }
                }
                BufferedImage img = new BufferedImage(pageSize.width, pageSize.height, BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics = img.createGraphics();
                // clear the drawing area
                graphics.setPaint(Color.white);
                graphics.fill(new Rectangle2D.Float(0, 0, pageSize.width, pageSize.height));

                // render
                ppt.getSlides().get(i).draw(graphics);

                // save the output
                String filePath = web.fileUploadBaseDir + imgPath + "/" + (i + 1) + ".png";
                File file = new File(filePath);
                file.getParentFile().mkdirs();
                file.createNewFile();
                FileOutputStream out = new FileOutputStream(file);
                javax.imageio.ImageIO.write(img, "png", out);
                out.close();

                outList.add(filePath);
            }
        } catch (Exception e) {
            throw new CommonException(ResultCodeEnum.UNKNOWN_ERROR, "ppt转图片未知错误", e);
        }

        return outList;
    }
}
