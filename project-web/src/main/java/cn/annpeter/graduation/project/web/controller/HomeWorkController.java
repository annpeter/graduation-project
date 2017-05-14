package cn.annpeter.graduation.project.web.controller;

import cn.annpeter.graduation.project.base.common.model.ResultModel;
import cn.annpeter.graduation.project.core.service.HomeWorkService;
import cn.annpeter.graduation.project.dal.model.User;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Min;
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
     * @api {get} /api/homework/list 可用作业列表
     * @apiName list
     * @apiGroup HomeWork
     *
     * @apiParam {int} currPage  当前页(默认值0)
     * @apiParam {int} pageSize  页的大小(默认值10)
     *
     * @apiSuccessExample {json} Response 200 Example
     *   {
     *     "code": 200,
     *     "data": {
     *       "currPage": 0,
     *       "sliderList": [
     *         "1"
     *       ],
     *       "prePage": 1,
     *       "nextPage": 1,
     *       "dataList": [
     *         {
     *           "id": 4,
     *           "courseId": 1,
     *           "title": "请同学们于4月底完成Word测试练习",
     *           "url": "/fileUpload/1/2017-05-02/230.docx",
     *           "state": 1,
     *           "createTime": "2017-05-02 10:40:52",
     *           "updateTime": "2017-05-02 10:40:52"
     *         }
     *       ]
     *     },
     *     "resultMsg": "执行成功",
     *     "errorStackTrace": null
     *   }
     */
    // @formatter:on
    @GetMapping(value = "list")
    public ResultModel getHomeWorkList(@RequestParam(defaultValue = "0") int currPage,
                                       @Min(message = "pageSize 最小为1", value = 1)
                                       @RequestParam(defaultValue = "10") int pageSize,
                                       HttpSession session) {
        User sessionUser = (User) session.getAttribute(web.loggedUserInfo);
        Integer teacherId = null;
        if (sessionUser.getTeacherId() == null && sessionUser.getIsAdmin() == 1) {
            teacherId = sessionUser.getId();
        }

        return ResultModel.success(homeWorkService.getHomeWorkListByTeacherId(currPage, pageSize, teacherId));
    }


    // @formatter:off
    /**
     * @api {get} /api/homework/commitList 学生完成作业列表
     * @apiName commitList
     * @apiGroup HomeWork
     *
     * @apiParam {int} currPage  当前页(默认值0)
     * @apiParam {int} pageSize  页的大小(默认值10)
     * @apiParam {int} homeWorkId 作业id
     *
     * @apiSuccessExample {json} Response 200 Example
     * {
     *   "code": 200,
     *   "data": {
     *     "currPage": 0,
     *     "sliderList": [
     *       "1"
     *     ],
     *     "prePage": 1,
     *     "nextPage": 1,
     *     "dataList": [
     *       {
     *         "id": 1,
     *         "userId": 1,
     *         "url": "www.baidu.com",
     *         "score": null,
     *         "comment": null,
     *         "homeWorkId": 1,
     *         "createTime": "2017-05-10 12:22:27",
     *         "updateTime": "2017-05-10 12:22:27"
     *       }
     *     ]
     *   },
     *   "resultMsg": "执行成功",
     *   "errorStackTrace": null
     * }
     */
    // @formatter:on
    @GetMapping(value = "commitList")
    public ResultModel getHomeWorkCommitList(@RequestParam(defaultValue = "0") Integer currPage,
                                             @Min(message = "pageSize 最小为1", value = 1)
                                             @RequestParam(defaultValue = "10") Integer pageSize,
                                             @NotNull(message = "homeWorkId不能为空") Integer homeWorkId,
                                             HttpSession session) {
        User sessionUser = (User) session.getAttribute(web.loggedUserInfo);

        return ResultModel.success(homeWorkService.getHomeWorkCommitListByHomeWorkId(currPage, pageSize, homeWorkId, sessionUser.getId()));
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
    public ResultModel commitHomeWork(HttpSession session,
                                      @NotNull(message = "homeWorkId不能为空") Integer homeWorkId,
                                      @NotEmpty(message = "url不能为空") String url) {
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
    public ResultModel addHomeWork(@NotNull(message = "courseId不能为空") Integer courseId,
                                   @NotEmpty(message = "title不能为空") String title,
                                   @NotEmpty(message = "url不能为空") String url,
                                   HttpSession session) {
        User user = (User) session.getAttribute(web.loggedUserInfo);
        homeWorkService.addHomeWork(courseId, title, url, user.getId());
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
