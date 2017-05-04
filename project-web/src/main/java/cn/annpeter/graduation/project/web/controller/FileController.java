package cn.annpeter.graduation.project.web.controller;

import cn.annpeter.graduation.project.base.common.model.ResultCodeEnum;
import cn.annpeter.graduation.project.base.common.model.ResultModel;
import cn.annpeter.graduation.project.dal.model.User;
import cn.annpeter.graduation.project.dal.bean.vo.FileUploadResultVO;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static cn.annpeter.graduation.project.core.config.GlobalConfig.web;

/**
 * Created on 2017/03/12
 *
 * @author annpeter.it@gmail.com
 */
@Validated
@RestController
@RequestMapping("/api/file")
public class FileController {

    // @formatter:off
    /**
     * @api {post} /api/file/upload/single 上传单个文件
     * @apiName uploadSingle
     * @apiGroup Upload
     *
     * @apiParam {MultipartFile} file 文件(MultipartFile的key必须为file)
     *
     * @apiSuccess (Code) {string} 200 上传成功
     * @apiSuccessExample {json} Response 200 Example
     *  {
     *      "code": 200,
     *      "data": {
     *          "file_url": "http://upload.annpeter.cn/1/2017-03-12/245.xlsx"
     *      },
     *      "result_msg": "执行成功",
     *      "error_stack_trace": null
     *  }
     */
    // @formatter:on
    @PostMapping(value = "/upload/single")
    public ResultModel<Map> uploadSingle(@RequestParam("file") MultipartFile file, HttpSession session) throws IOException {
        if (file == null) {
            return ResultModel.fail(ResultCodeEnum.RESOURCE_NOT_FOUND, "上传文件不能为空");
        }
        Integer userId = ((User) session.getAttribute(web.loggedUserInfo)).getId();
        String url = handleSingleFile(file, userId);
        return ResultModel.success(Stream.of(new SimpleEntry<>("file_url", url))
                .collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue)));
    }


    // @formatter:off
     /**
     * @api {post} /api/file/upload/multiple 上传多个文件
     * @apiName uploadMultiple
     * @apiGroup Upload
     *
     * @apiParam {MultipartFile} file 文件(MultipartFile的key可随意规定)
     *
     * @apiSuccess (Code) {string} 200 上传成功
     * @apiSuccessExample {json} Response 200 Example
     *  {
     *      "code": 200,
     *      "data": [
     *          {
     *            "filename": "257.pdf",
     *            "file_url": "http://upload.annpeter.cn/1/2017-03-12/257_1706.pdf"
     *          },
     *          {
     *            "filename": "Alibaba-Java-minibook-v1.1.0.pdf",
     *            "file_url": "http://upload.annpeter.cn/1/2017-03-12/Alibaba-Java-minibook-v1.1.0.pdf"
     *          }
     *      ],
     *      "result_msg": "执行成功",
     *      "error_stack_trace": null
     *  }
     */
     // @formatter:on
     @PostMapping(value = "/file/multiple")
     public ResultModel<FileUploadResultVO> uploadMultiple(HttpServletRequest request) throws Exception {
         FileUploadResultVO result = new FileUploadResultVO();

         Integer userId = ((User) request.getSession().getAttribute(web.loggedUserFlag)).getId();

         // 创建一个通用的多部分解析器
         CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
         // 判断 request 是否有文件上传, 即多部分请求
         if (multipartResolver.isMultipart(request)) {
             // 转换成多部分request
             MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
             // 取得request中的所有文件名
             Iterator<String> it = multiRequest.getFileNames();

             while (it.hasNext()) {
                 // 取得上传文件
                 MultipartFile file = multiRequest.getFile(it.next());

                 String url = handleSingleFile(file, userId);
                 result.addFileItem(file.getOriginalFilename(), url);
             }
         }

         return ResultModel.success(result);
     }

    // 上传单个文件, 返回上传后的url全路径
    private String handleSingleFile(MultipartFile file, Integer userId) throws IOException {
        if (file != null) {
            // 取得当前上传文件的文件名称
            String fileFullName = file.getOriginalFilename();

            int pointIndex = fileFullName.lastIndexOf(".");
            int filenameLength = file.getOriginalFilename().length();
            pointIndex = pointIndex == -1 ? filenameLength : pointIndex;
            String filename = fileFullName.substring(0, pointIndex);
            String fileSuffix = fileFullName.substring(pointIndex, filenameLength);

            if (StringUtils.isNotEmpty(filename)) {
                String fileUri = userId + "/" + DateFormatUtils.ISO_DATE_FORMAT.format(new Date()) + "/";

                File localFile = getUseAbleFile(fileUri, filename, fileSuffix);
                file.transferTo(localFile);

                return "/fileUpload/" + fileUri + localFile.getName();
            }
        }

        return null;
    }

    // 获取一个没有被使用的file用于文件上传时保存文件
    private File getUseAbleFile(String fileUri, String filename, String suffix) throws IOException {
        String filePath = web.fileUploadBaseDir + fileUri;
        File localFile = new File(filePath, filename + suffix);
        localFile.getParentFile().mkdirs();
        if (localFile.createNewFile()) {
            return localFile;
        } else {
            // 当文件名重复的时候, 采用添加后缀的形式
            return getUseAbleFile(fileUri, filename + "_" + RandomStringUtils.randomNumeric(4), suffix);
        }
    }

}
