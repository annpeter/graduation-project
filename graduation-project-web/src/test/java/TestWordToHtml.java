import cn.annpeter.graduation.project.base.common.util.EncryptUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.Test;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import static cn.annpeter.graduation.project.core.config.GlobalConfig.web;

/**
 * Created on 2017/05/01
 *
 * @author annpeter.it@gmail.com
 */
public class TestWordToHtml {

    @Test
    public void docToHtml() throws Exception {
        String sourceFileName = "/Users/annpeter/Documents/MyCode/JAVA/graduation-project/graduation-project-web/src/test/resources/test.doc";
        String targetFileName = "/Users/annpeter/Documents/MyCode/JAVA/graduation-project/graduation-project-web/src/test/resources/test.html";
        String imagePathStr = "/Users/annpeter/Documents/MyCode/JAVA/graduation-project/graduation-project-web/src/test/resources";
        HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(sourceFileName));
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(document);
        // 保存图片，并返回图片的相对路径
        wordToHtmlConverter.setPicturesManager((content, pictureType, name, width, height) -> {
            try (FileOutputStream out = new FileOutputStream(imagePathStr + name)) {
                out.write(content);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "image/" + name;
        });
        wordToHtmlConverter.processDocument(wordDocument);
        Document htmlDocument = wordToHtmlConverter.getDocument();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(new File(targetFileName));

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        serializer.transform(domSource, streamResult);
    }

    // docx转换为html
    @Test
    public void docxToHtml() throws Exception {
        String sourceFileName = "http://localhost:8888/test.docx";

        OutputStreamWriter outputStreamWriter;
        URL url = new URL(sourceFileName);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        String imgPath = "/docxImg/" + EncryptUtils.MD5(sourceFileName);

        try (InputStream inputStream = conn.getInputStream();
             OutputStream htmlOutputStream = new ByteArrayOutputStream()) {

            XWPFDocument document = new XWPFDocument(inputStream);
            XHTMLOptions options = XHTMLOptions.create();
            // 存放图片的文件夹
            options.setExtractor(new FileImageExtractor(new File(web.fileUploadBaseDir + imgPath)));
            // html中图片的路径
            options.URIResolver(new BasicURIResolver(imgPath));
            outputStreamWriter = new OutputStreamWriter(htmlOutputStream, "utf-8");
            XHTMLConverter xhtmlConverter = (XHTMLConverter) XHTMLConverter.getInstance();
            xhtmlConverter.convert(document, outputStreamWriter, options);

            System.out.println(htmlOutputStream.toString());
        }
    }
}