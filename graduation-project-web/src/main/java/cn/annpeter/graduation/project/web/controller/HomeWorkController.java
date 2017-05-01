package cn.annpeter.graduation.project.web.controller;

import cn.annpeter.graduation.project.base.common.model.ResultModel;
import cn.annpeter.graduation.project.core.service.HomeWorkService;
import cn.annpeter.graduation.project.dal.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static cn.annpeter.graduation.project.core.config.GlobalConfig.web;

/**
 * Created on 2017/04/22
 *
 * @author annpeter.it@gmail.com
 */
@RestController
@RequestMapping("/api/homework")
@Produces(MediaType.APPLICATION_JSON)
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
    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.GET)
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
    @ResponseBody
    @RequestMapping(value = "commit", method = RequestMethod.POST)
    public ResultModel commitHomeWork(HttpSession session, Integer homeWorkId, String url) {
        User sessionUser = (User) session.getAttribute(web.loggedUserInfo);
        homeWorkService.commitHomeWork(sessionUser.getId(), homeWorkId, url);
        return ResultModel.success();
    }


    // @formatter:off
    /**
     * @api {post} /api/homework/add 添加课程
     * @apiName add
     * @apiGroup Course
     *
     * @apiParam {string} name 课程名字
     * @apiParam {string} imgUrl 课程logo
     * @apiParam {string} intro 课程简介
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
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResultModel addHomeWork(@NotNull Integer courseId,
                                   @NotNull String title,
                                   @NotNull String url,
                                   @NotNull Integer state) {
        homeWorkService.addHomeWork(courseId, title, url, state);
        return ResultModel.success(null, "添加成功");
    }
}
