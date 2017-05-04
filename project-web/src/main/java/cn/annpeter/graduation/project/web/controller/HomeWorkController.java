package cn.annpeter.graduation.project.web.controller;

import cn.annpeter.graduation.project.base.common.model.ResultModel;
import cn.annpeter.graduation.project.core.service.HomeWorkService;
import cn.annpeter.graduation.project.dal.model.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

import static cn.annpeter.graduation.project.core.config.GlobalConfig.web;

/**
 * Created on 2017/04/22
 *
 * @author annpeter.it@gmail.com
 */
@Validated
@RestController
@RequestMapping("/api/homework")
public class HomeWorkController {

    @Resource
    private HomeWorkService homeWorkService;

    // @formatter:off
    /**
     * @api {post} /api/homework/list 可用作业列表
     * @apiName list
     * @apiGroup HomeWork
     *
     * @apiSuccessExample {json} Response 200 Example
     * {
     *     "code": 200,
     *     "data": {
     *          "id": 1,
     *          "course_id": 1,
     *          "title": "作业标题",
     *          "url": "http://www.baidu.com",
     *          "state": 1
     *      },
     *     "result_msg": "执行成功",
     *     "error_stack_trace": null
     * }
     */
    // @formatter:on
    @GetMapping(value = "list")
    public ResultModel getResourceList(@NotNull Integer courseId) {
        return ResultModel.success(homeWorkService.getHomeWorkListByCourseId(courseId));
    }

    // @formatter:off
    /**
     * @api {post} /api/homework/commit 提交作业
     * @apiName commit
     * @apiGroup HomeWork
     *
     * @apiParam {int} homeWorkId 作业id
     * @apiParam {url} url 作业url
     *
     * @apiSuccessExample {json} Response 200 Example
     * {
     *     "code": 200,
     *     "data": {
     *          "id": 1,
     *          "course_id": 1,
     *          "title": "作业标题",
     *          "url": "http://www.baidu.com",
     *          "state": 1
     *      },
     *     "result_msg": "执行成功",
     *     "error_stack_trace": null
     * }
     */
    // @formatter:on
    @PostMapping(value = "commit")
    public ResultModel commitHomeWork(HttpSession session, Integer homeWorkId, String url) {
        User sessionUser = (User) session.getAttribute(web.loggedUserInfo);
        homeWorkService.commitHomeWork(sessionUser.getId(), homeWorkId, url);
        return ResultModel.success();
    }


    // @formatter:off
    /**
     * @api {post} /api/homework/add 添加作业
     * @apiName add
     * @apiGroup HomeWork
     *
     * @apiParam {int} courseId 课程Id
     * @apiParam {string} title 作业title
     * @apiParam {string} url 作业文档url
     *
     * @apiSuccessExample {json} Response 200 Example
     * {
     *     "code": 200,
     *     "data": null,
     *     "result_msg": "添加成功",
     *     "error_stack_trace": null
     * }
     */
    // @formatter:on
    @PostMapping(value = "add")
    public ResultModel addHomeWork(@NotNull Integer courseId,
                                   @NotNull String title,
                                   @NotNull String url) {
        homeWorkService.addHomeWork(courseId, title, url);
        return ResultModel.success(null, "添加成功");
    }


    // @formatter:off
    /**
     * @api {post} /api/homework/check 批改作业
     * @apiName check
     * @apiGroup HomeWork
     *
     * @apiParam {int} homeWorkCommitId 作业id
     *
     * @apiSuccessExample {json} Response 200 Example
     * {
     *     "code": 200,
     *     "data": null,
     *     "result_msg": "提交成功",
     *     "error_stack_trace": null
     * }
     */
    // @formatter:on
    @PostMapping(value = "check")
    public ResultModel checkHomeWork(@NotNull(message = "homeWorkCommitId不能为空") Integer homeWorkCommitId,
                                     @NotNull(message = "score分数") Float score,
                                     String comment) {
        homeWorkService.checkHomeWorkCommit(homeWorkCommitId, score, comment);
        return ResultModel.success(null, "提交成功");
    }
}
