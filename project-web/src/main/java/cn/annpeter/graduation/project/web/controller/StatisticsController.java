package cn.annpeter.graduation.project.web.controller;

import cn.annpeter.graduation.project.base.common.model.ResultModel;
import cn.annpeter.graduation.project.core.service.CountingService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created on 2017/04/30
 *
 * @author annpeter.it@gmail.com
 */
@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {


    @Resource
    private CountingService countingService;

    // @formatter:off
    /**
     * @api {post} /api/course/list 课程列表
     * @apiName list
     * @apiGroup Course
     *
     * @apiSuccessExample {json} Response 200 Example
     * {
     *   "code": 200,
     *   "data": {
     *     "total": {
     *       "date": "2017-04-30",
     *       "view": 2
     *     },
     *     "today": {
     *       "date": "2017-04-30",
     *       "view": 2
     *     },
     *     "days": [
     *       {
     *         "date": "2017-04-01",
     *         "view": 0
     *       },
     *       {
     *         "date": "2017-03-31",
     *         "view": 3
     *       }
     *     ]
     *   },
     *   "result_msg": "执行成功",
     *   "error_stack_trace": null
     * }
     */
    // @formatter:on
    @GetMapping(value = "get")
    public ResultModel getStatistics() {
        return ResultModel.success(countingService.getUserConnectionInfoVO());
    }

}
