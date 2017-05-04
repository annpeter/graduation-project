package cn.annpeter.graduation.project.web.controller;

import cn.annpeter.graduation.project.base.common.model.ResultModel;
import cn.annpeter.graduation.project.core.service.QuestionService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
     * @apiParam {int} currPage  当前页(默认值0)
     * @apiParam {int} pageSize  页的大小(默认值10)
     * @apiParam {int} courseId  课程id
     *
     * @apiSuccessExample {json} Response 200 Example
     *  {
     *    "code": 200,
     *    "data": {
     *      "currPage": 0,
     *      "sliderList": [
     *        "1"
     *      ],
     *      "prePage": 1,
     *      "nextPage": 1,
     *      "dataList": [
     *        {
     *          "id": 6,
     *          "content": "你好 我有个问题",
     *          "answer": null,
     *          "createTime": "2017-05-03 17:02:57",
     *          "updateTime": "2017-05-03 17:02:57",
     *          "courseId": 1
     *        }
     *      ]
     *    },
     *    "resultMsg": "执行成功",
     *    "errorStackTrace": null
     *  }
     */
    // @formatter:on
    @GetMapping(value = "list")
    public ResultModel getQuestionList(@RequestParam(defaultValue = "0") int currPage,
                                       @Min(message = "pageSize 最小为1", value = 1)
                                       @RequestParam(defaultValue = "10") int pageSize,
                                       @NotNull(message = "courseId不能为空") Integer courseId) {
        return ResultModel.success(questionService.list(currPage, pageSize, courseId));
    }


}
