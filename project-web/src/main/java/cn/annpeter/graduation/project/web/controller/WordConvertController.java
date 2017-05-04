package cn.annpeter.graduation.project.web.controller;

import cn.annpeter.graduation.project.base.common.model.ResultModel;
import cn.annpeter.graduation.project.core.service.WordConvertService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created on 2017/05/01
 *
 * @author annpeter.it@gmail.com
 */
@Validated
@RestController
@RequestMapping("/api/word")
public class WordConvertController {

    @Resource
    private WordConvertService wordConvertService;

    // @formatter:off
    /**
     * @api {post} /api/word/convert word转换为html
     * @apiName convert
     * @apiGroup Word
     *
     *  @apiParam {string} url word路径
     *
     * @apiSuccessExample {json} Response 200 Example
     *  {
     *      "code": 200,
     *      "data": "html",
     *      "result_msg": "获取成功",
     *      "error_stack_trace": null
     *  }
     */
    // @formatter:on
    @PostMapping(value = "convert")
    public ResultModel convert(String url) {
        return ResultModel.success(wordConvertService.docxToHtml(url));
    }

}
