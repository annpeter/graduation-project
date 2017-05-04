package cn.annpeter.graduation.project.web.controller;

import cn.annpeter.graduation.project.base.common.model.ResultModel;
import cn.annpeter.graduation.project.core.service.NoticeService;
import cn.annpeter.graduation.project.core.service.ResourceService;
import com.sun.istack.internal.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Created on 2017/04/15
 *
 * @author annpeter.it@gmail.com
 */
@Validated
@RestController
@RequestMapping("/api/resource")
public class ResourceController {

    @Resource
    private ResourceService resourceService;

    // @formatter:off
    /**
     * @api {post} /api/resource/list 资源列表
     * @apiName list
     * @apiGroup Resource
     *
     * @apiParam {int} currPage  当前页(默认值0)
     * @apiParam {int} pageSize  页的大小(默认值10)
     *
     * @apiSuccessExample {json} Response 200 Example
     * {
     *     "code": 200,
     *     "data": {
     *         "id": 1,
     *         "type": "公共资源",
     *         "name": "你懂的",
     *         "url": "http://www.baidu.com",
     *         "course_id": 2,
     *         "create_time": "2017-04-22 15:30:14",
     *         "update_time": "2017-04-22 15:46:20"
     *     },
     *     "result_msg": "执行成功",
     *     "error_stack_trace": null
     * }
     */
    // @formatter:on
    @GetMapping(value = "list")
    public ResultModel getResourceList(@DefaultValue("0") @QueryParam("curr_page") int currPage,
                                       @Min(message = "page_size 最小为1", value = 1)
                                       @DefaultValue("10") @QueryParam("page_size") int pageSize){
        return ResultModel.success(resourceService.getResourceList(currPage, pageSize));
    }


    // @formatter:off
    /**
     * @api {post} /api/resource/add 添加资源
     * @apiName add
     * @apiGroup Resource
     *
     * @apiParam {string} type 资源类型
     * @apiParam {string} name 资源名称
     * @apiParam {string} url  资源URL
     * @apiParam {int} courseId  课程id
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
    public ResultModel addResource(String type, String name, String url, Integer courseId) {
        resourceService.addResource(type, name, url, courseId);
        return ResultModel.success();
    }
}
