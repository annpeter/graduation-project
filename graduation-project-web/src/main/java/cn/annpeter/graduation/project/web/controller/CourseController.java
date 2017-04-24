package cn.annpeter.graduation.project.web.controller;

import cn.annpeter.graduation.project.base.common.model.ResultModel;
import cn.annpeter.graduation.project.core.service.CourseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created on 2017/04/15
 *
 * @author annpeter.it@gmail.com
 */
@RestController
@RequestMapping("/api/course")
@Produces(MediaType.APPLICATION_JSON)
public class CourseController {

    @Resource
    private CourseService courseService;

    // @formatter:off
    /**
     * @api {post} /api/course/list 课程列表
     * @apiName list
     * @apiGroup Course
     *
     * @apiSuccessExample {json} Response 200 Example
     * {
     *     "code": 200,
     *     "data": [
     *       {
     *         "id": 1,
     *         "name": "软件工程",
     *         "seq": 0,
     *         "intro": "这是软件工程吃介绍啊。。。。",
     *         "create_time": "2017-04-15 16:58:59",
     *         "update_time": "2017-04-15 16:59:18"
     *       }
     *     ],
     *     "result_msg": "执行成功",
     *     "error_stack_trace": null
     * }
     */
    // @formatter:on
    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResultModel getCourseList() {
        return ResultModel.success(courseService.getCourseList());
    }


    // @formatter:off
    /**
     * @api {post} /api/course/info 课程信息
     * @apiName info
     * @apiGroup Course
     *
     * @apiParam {int} courseId 课程id
     *
     * @apiSuccessExample {json} Response 200 Example
     * {
     *     "code": 200,
     *     "data": {
     *         "id": 1,
     *         "name": "软件工程",
     *         "seq": 0,
     *         "intro": "这是软件工程吃介绍啊。。。。",
     *         "create_time": "2017-04-15 16:58:59",
     *         "update_time": "2017-04-15 16:59:18"
     *     },
     *     "result_msg": "执行成功",
     *     "error_stack_trace": null
     * }
     */
    // @formatter:on
    @ResponseBody
    @RequestMapping(value = "info", method = RequestMethod.GET)
    public ResultModel getCourseInfo(@NotNull Integer courseId) {
        return ResultModel.success(courseService.getCourseInfo(courseId));
    }
}
