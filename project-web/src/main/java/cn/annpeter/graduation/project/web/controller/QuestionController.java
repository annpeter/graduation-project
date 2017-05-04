package cn.annpeter.graduation.project.web.controller;

import cn.annpeter.graduation.project.base.common.model.ResultModel;
import cn.annpeter.graduation.project.core.service.QuestionService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created on 2017/04/23
 *
 * @author annpeter.it@gmail.com
 */
@Validated
@RestController
@RequestMapping("/api/question")
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
     * @apiParam {int} courseId 课程id
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
    @PostMapping(value = "add")
    public ResultModel<String> addQuestion(String content, Integer courseId) {
        questionService.insertQuestion(content, courseId);
        return ResultModel.success(null, "添加成功");
    }


    // @formatter:off
    /**
     * @api {post} /api/question/answer 回答问题
     * @apiName answer
     * @apiGroup Question
     *
     * @apiParam {string} answer 回答内容
     * @apiParam {int} questionId 问题id
     *
     * @apiSuccessExample {json} Response 200 Example
     *  {
     *      "code": 200,
     *      "data": null,
     *      "result_msg": "回答成功",
     *      "error_stack_trace": null
     *  }
     */
    // @formatter:on
    @PostMapping(value = "answer")
    public ResultModel<String> answerQuestion(Integer questionId, String answer) {
        questionService.updateAnswer(answer, questionId);
        return ResultModel.success(null, "回答成功");
    }

    // @formatter:off
    /**
     * @api {post} /api/question/list 问题列表
     * @apiName list
     * @apiGroup Question
     *
     * @apiParam {int} courseId 课程id
     *
     * @apiSuccessExample {json} Response 200 Example
     *  {
     *    "code": 200,
     *    "data": [
     *      {
     *        "id": 1,
     *        "content": "问题内容",
     *        "answer": null,
     *        "create_time": null,
     *        "update_time": "2017-05-01 13:23:44",
     *        "course_id": 1
     *      }
     *    ],
     *    "result_msg": "执行成功",
     *    "error_stack_trace": null
     *  }
     */
    // @formatter:on
    @GetMapping(value = "list")
    public ResultModel getQuestionList(Integer courseId) {
        return ResultModel.success(questionService.list(courseId));
    }


}
