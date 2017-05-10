package cn.annpeter.graduation.project.core.service;

import cn.annpeter.graduation.project.base.common.exception.CommonException;
import cn.annpeter.graduation.project.base.common.model.ResultCodeEnum;
import cn.annpeter.graduation.project.base.common.util.EncryptUtils;
import cn.annpeter.graduation.project.core.service.helper.CacheTemplateService;
import org.apache.poi.hslf.usermodel.HSLFSlide;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.hslf.usermodel.HSLFTextParagraph;
import org.apache.poi.hslf.usermodel.HSLFTextRun;
import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
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


    public List<String> convertPPTtoImage(String sourceFileName) {
        List<String> imgNamesList = new ArrayList<>();
        String key = EncryptUtils.MD5(sourceFileName);
        String imgPath = "/docxImg/" + key;

        try (InputStream inputStream = (new URL(sourceFileName).openConnection()).getInputStream();
             HSLFSlideShow oneHSLFSlideShow = new HSLFSlideShow(inputStream)) {

            // 获取PPT每页的大小（宽和高度）
            Dimension onePPTPageSize = oneHSLFSlideShow.getPageSize();

            // 获得PPT文件中的所有的PPT页面（获得每一张幻灯片）,并转为一张张的播放片
            List<HSLFSlide> pptPageSlideList = oneHSLFSlideShow.getSlides();
            // 下面循环的主要功能是实现对PPT文件中的每一张幻灯片进行转换和操作
            for (int i = 0; i < pptPageSlideList.size(); i++) {
                // 这几个循环只要是设置字体为宋体，防止中文乱码，
                List<List<HSLFTextParagraph>> oneTextParagraphs = pptPageSlideList.get(i).getTextParagraphs();
                for (List<HSLFTextParagraph> list : oneTextParagraphs) {
                    for (HSLFTextParagraph hslfTextParagraph : list) {
                        List<HSLFTextRun> HSLFTextRunList = hslfTextParagraph.getTextRuns();
                        for (int j = 0; j < HSLFTextRunList.size(); j++) {

                            Double size = HSLFTextRunList.get(j).getFontSize();
                            if ((size <= 0) || (size >= 26040)) {
                                HSLFTextRunList.get(j).setFontSize(20.0);
                            }

                            HSLFTextRunList.get(j).setFontFamily("宋体");
                        }
                    }
                }

                BufferedImage oneBufferedImage = new BufferedImage(onePPTPageSize.width, onePPTPageSize.height, BufferedImage.TYPE_INT_RGB);
                Graphics2D oneGraphics2D = oneBufferedImage.createGraphics();

                oneGraphics2D.setPaint(Color.white);
                oneGraphics2D.fill(new Rectangle2D.Float(0, 0, onePPTPageSize.width, onePPTPageSize.height));
                pptPageSlideList.get(i).draw(oneGraphics2D);


                String imgName = imgPath + "/" + (i + 1) + ".png";
                imgNamesList.add("/fileUpload" + imgName);

                File file = new File(web.fileUploadBaseDir + imgName);
                file.getParentFile().mkdirs();
                file.createNewFile();

                FileOutputStream originalPPTFileOutStream = new FileOutputStream(file);
                ImageIO.write(oneBufferedImage, "png", originalPPTFileOutStream);
                originalPPTFileOutStream.close();
            }
        } catch (Exception ex) {
            throw new CommonException(ResultCodeEnum.UNKNOWN_ERROR, "ppt转图片未知错误", ex);
        }

        return imgNamesList;
    }
}
