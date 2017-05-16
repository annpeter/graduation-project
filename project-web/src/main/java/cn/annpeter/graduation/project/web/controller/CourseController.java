package cn.annpeter.graduation.project.web.controller;

import cn.annpeter.graduation.project.base.common.model.ResultModel;
import cn.annpeter.graduation.project.core.service.CourseService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created on 2017/04/15
 *
 * @author annpeter.it@gmail.com
 */
@Validated
@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Resource
    private CourseService courseService;

    // @formatter:off
    /**
     * @api {get} /api/course/list 课程列表
     * @apiName list
     * @apiGroup Course
     *
     * @apiParam {int} currPage  当前页(默认值0)
     * @apiParam {int} pageSize  页的大小(默认值10)
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
    @GetMapping(value = "list")
    public ResultModel getCourseList(@RequestParam(defaultValue = "0") Integer currPage,
                                     @Min(message = "pageSize 最小为1", value = 1)
                                     @RequestParam(defaultValue = "10") Integer pageSize) {
        return ResultModel.success(courseService.getCourseList(currPage, pageSize));
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
    @GetMapping(value = "info")
    public ResultModel getCourseInfo(@NotNull Integer courseId) {
        return ResultModel.success(courseService.getCourseInfo(courseId));
    }


    // @formatter:off
    /**
     * @api {get} /api/course/infoByName 根据课程名称获取信息
     * @apiName infoByName
     * @apiGroup Course
     *
     * @apiParam {string} courseName 课程名
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
    @GetMapping(value = "infoByName")
    public ResultModel getCourseInfoByName(@NotEmpty String courseName) {
        return ResultModel.success(courseService.getCourseInfo(courseName));
    }


    // @formatter:off
    /**
     * @api {post} /api/course/add 添加课程
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
    @PostMapping(value = "add")
    public ResultModel addCourse(@NotEmpty(message = "name不能为空") String name,
                                 @NotEmpty(message = "imgUrl不能为空") String imgUrl,
                                 @NotEmpty(message = "intro不能为空") String intro) {
        courseService.addCourse(name, imgUrl, intro);
        return ResultModel.success(null, "添加成功");
    }


    // @formatter:off
    /**
     * @api {get} /api/course/delete 课程删除
     * @apiName delete
     * @apiGroup Course
     *
     * @apiParam {int} courseId 课程id
     *
     * @apiSuccessExample {json} Response 200 Example
     * {
     *     "code": 200,
     *     "data": null,
     *     "result_msg": "删除成功",
     *     "error_stack_trace": null
     * }
     */
    // @formatter:on
    @GetMapping(value = "delete")
    public ResultModel delete(@NotNull(message = "courseId不能为空") Integer courseId) {
        courseService.delete(courseId);
        return ResultModel.success(null, "删除成功");
    }
}
