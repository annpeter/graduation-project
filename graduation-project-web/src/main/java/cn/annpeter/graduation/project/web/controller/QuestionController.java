package cn.annpeter.graduation.project.web.controller;

import cn.annpeter.graduation.project.base.common.model.ResultModel;
import cn.annpeter.graduation.project.core.service.QuestionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created on 2017/04/23
 *
 * @author annpeter.it@gmail.com
 */
@RestController
@RequestMapping("/api/question")
@Produces(MediaType.APPLICATION_JSON)
public class QuestionController {

    @Resource
    private QuestionService questionService;

    // @formatter:off
    /**
     * @api {post} /api/question/add 添加问题
     * @apiName add
     * @apiGroup Question
     *
     * @apiParam {string} content 问题内容
     *
     * @apiSuccessExample {json} Response 200 Example
     *  {
     *      "code": 200,
     *      "data": null,
     *      "result_msg": "添加成功",
     *      "error_stack_trace": null
     *  }
     */
    // @formatter:on
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResultModel<String> addQuestion(String content) {
        questionService.insertQuestion(content);
        return ResultModel.success(null, "添加成功");
    }

}
