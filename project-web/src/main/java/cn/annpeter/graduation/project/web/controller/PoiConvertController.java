package cn.annpeter.graduation.project.web.controller;

import cn.annpeter.graduation.project.base.common.model.ResultModel;
import cn.annpeter.graduation.project.core.service.WordConvertService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created on 2017/05/01
 *
 * @author annpeter.it@gmail.com
 */
@Validated
@RestController
@RequestMapping("/api/poi")
public class PoiConvertController {

    @Resource
    private WordConvertService wordConvertService;

    // @formatter:off
    /**
     * @api {post} /api/poi/word word转换为html
     * @apiName poi
     * @apiGroup word
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
    @PostMapping(value = "word")
    public ResultModel word(@NotEmpty(message = "url不能为空") String url) {
        return ResultModel.success(wordConvertService.docxToHtml(url));
    }


    // @formatter:off
    /**
     * @api {post} /api/poi/ppt ppt转换图片
     * @apiName poi
     * @apiGroup ppt
     *
     *  @apiParam {string} url ppt路径
     *
     * @apiSuccessExample {json} Response 200 Example
     *  {
     *      "code": 200,
     *      "data": [
     *          "url"       图片url
     *      ],
     *      "result_msg": "获取成功",
     *      "error_stack_trace": null
     *  }
     */
    // @formatter:on
    @PostMapping(value = "ppt")
    public ResultModel ppt(@NotEmpty(message = "url不能为空") String url) {
        return ResultModel.success(wordConvertService.pptToImage(url));
    }


}
