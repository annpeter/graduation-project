package cn.annpeter.graduation.project.core.service;

import cn.annpeter.graduation.project.base.common.exception.CommonException;
import cn.annpeter.graduation.project.base.common.model.ResultCodeEnum;
import cn.annpeter.graduation.project.base.common.util.EncryptUtils;
import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;

import static cn.annpeter.graduation.project.core.config.GlobalConfig.web;

/**
 * Created on 2017/05/01
 *
 * @author annpeter.it@gmail.com
 */
@Service
public class WordConvertService {

    public String docxToHtml(String sourceFileName) {

        OutputStreamWriter outputStreamWriter;
        String imgPath = "/docxImg/" + EncryptUtils.MD5(sourceFileName);

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
    }

}
