package cn.annpeter.graduation.project.web.controller;

import cn.annpeter.graduation.project.base.common.model.ResultModel;
import cn.annpeter.graduation.project.core.service.NoticeService;
import com.sun.istack.internal.NotNull;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created on 2017/04/15
 *
 * @author annpeter.it@gmail.com
 */
@RestController
@RequestMapping("/api/notice")
public class NoticeController {

    @Resource
    private NoticeService noticeService;

    // @formatter:off
    /**
     * @api {post} /api/notice/list 公告列表
     * @apiName list
     * @apiGroup Notice
     *
     * @apiSuccessExample {json} Response 200 Example
     * {
     *     "code": 200,
     *     "data": [
     *       {
     *         "id": 1,
     *         "type": 0,                   公告类型(0: 校内公告, 1:院内公告)
     *         "course_id": 1,
     *         "title": "公告标题呀",
     *         "content": "这是公告的内容呀",
     *         "create_time": "2017-04-15 16:58:04",
     *         "update_time": "2017-04-22 14:44:21"
     *       }
     *     ],
     *     "result_msg": "执行成功",
     *     "error_stack_trace": null
     * }
     */
    // @formatter:on
    @GetMapping(value = "list")
    public ResultModel getNoticeList(@NotNull Integer courseId){
        return ResultModel.success(noticeService.getNoticeListByCourseId(courseId));
    }

    // @formatter:off
    /**
     * @api {post} /api/notice/add 添加公告
     * @apiName add
     * @apiGroup Notice
     *
     * @apiParam {int} courseId 课程id
     * @apiParam {int} type 公告类型(0: 校内公告, 1:院内公告)
     * @apiParam {string} content 公告内容
     *
     * @apiSuccessExample {json} Response 200 Example
     * {
     *     "code": 200,
     *     "data": null,
     *     "result_msg": "执行成功",
     *     "error_stack_trace": null
     * }
     */
    // @formatter:on
    @PostMapping(value = "add")
    public ResultModel addNotice(@NotNull Integer courseId,
                                 @NotNull Integer type,
                                 @NotNull String title,
                                 @NotNull String content){
        noticeService.addNotice(courseId, type, title, content);
        return ResultModel.success(null, "添加成功");
    }
}
