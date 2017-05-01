package cn.annpeter.graduation.project.web.controller;

import cn.annpeter.graduation.project.base.common.model.ResultModel;
import cn.annpeter.graduation.project.core.service.WordConvertService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created on 2017/05/01
 *
 * @author annpeter.it@gmail.com
 */
@RestController
@RequestMapping("/api/word")
@Produces(MediaType.APPLICATION_JSON)
public class WordConvertController {

    @Resource
    private WordConvertService wordConvertService;

    // @formatter:off
    /**
     * @api {post} /api/word/convert word转换为html
     * @apiName convert
     * @apiGroup Word
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
    @ResponseBody
    @RequestMapping(value = "convert", method = RequestMethod.GET)
    public ResultModel convert(String url) {
        return ResultModel.success(wordConvertService.docxToHtml(url));
    }

}
