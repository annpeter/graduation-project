package cn.annpeter.graduation.project.web.controller;

import cn.annpeter.graduation.project.base.common.model.ResultModel;
import cn.annpeter.graduation.project.core.service.ResourceService;
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
@RequestMapping("/api/resource")
public class ResourceController {

    @Resource
    private ResourceService resourceService;

    // @formatter:off
    /**
     * @api {get} /api/resource/list 资源列表
     * @apiName list
     * @apiGroup Resource
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
     *          "id": 4,
     *          "type": "公共资源",
     *          "name": "江苏科技大学博士、硕士学位论文抽检评议结果处理办法",
     *          "url": "/fileUpload/1/2017-05-02/225.docx",
     *          "courseId": 2,
     *          "createTime": "2017-05-02 10:20:05",
     *          "updateTime": "2017-05-02 10:20:03"
     *        },
     *        {
     *          "id": 1,
     *          "type": "公共资源",
     *          "name": "国务院学位委员会关于开展2017年博士硕士学位授权审核工作的通知",
     *          "url": "/fileUpload/1/2017-05-02/245.docx",
     *          "courseId": 1,
     *          "createTime": "2017-04-22 15:30:14",
     *          "updateTime": "2017-05-02 09:23:39"
     *        }
     *      ]
     *    },
     *    "resultMsg": "执行成功",
     *    "errorStackTrace": null
     *  }
     */
    // @formatter:on
    @GetMapping(value = "list")
    public ResultModel getResourceList(@RequestParam(defaultValue = "0") Integer currPage,
                                       @Min(message = "pageSize 最小为1", value = 1)
                                       @RequestParam(defaultValue = "10") Integer pageSize,
                                       @NotNull(message = "courseId不能为空") Integer courseId) {
        return ResultModel.success(resourceService.getResourceList(currPage, pageSize, courseId));
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
    public ResultModel addResource(@NotEmpty(message = "type不能为空") String type,
                                   @NotEmpty(message = "name不能为空") String name,
                                   @NotEmpty(message = "url不能为空") String url,
                                   @NotNull(message = "courseId不能为空") Integer courseId) {
        resourceService.addResource(type, name, url, courseId);
        return ResultModel.success();
    }
}
