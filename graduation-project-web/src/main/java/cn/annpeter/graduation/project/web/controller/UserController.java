package cn.annpeter.graduation.project.web.controller;

import cn.annpeter.graduation.project.base.common.exception.CommonException;
import cn.annpeter.graduation.project.base.common.model.ResultCodeEnum;
import cn.annpeter.graduation.project.base.common.model.ResultModel;
import cn.annpeter.graduation.project.core.service.UserService;
import cn.annpeter.graduation.project.dal.model.User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static cn.annpeter.graduation.project.core.config.GlobalConfig.web;

/**
 * Created on 2017/03/04
 *
 * @author annpeter.it@gmail.com
 */
@RestController
@RequestMapping("/api/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    @Resource
    private UserService userService;


    // @formatter:off
    /**
     * @api {post} /api/user/register 用户注册
     * @apiName register
     * @apiGroup User
     *
     * @apiParam {string} name 用户名称
     * @apiParam {string} pwd 用户密码
     *
     * @apiSuccess (Code) {string} 200 注册成功
     * @apiSuccess (Code) {string} 409 此用户名已被注册
     * @apiSuccessExample {json} Response 200 Example
     *  {
     *      "code": 200,
     *      "data": null,
     *      "result_msg": "注册成功",
     *      "error_stack_trace": null
     *  }
     */
    // @formatter:on
    @ResponseBody
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ResultModel register(@RequestParam("name") String name,
                                        @RequestParam("pwd") String pwd) {
        User user = new User();
        user.setName(name);
        user.setPwd(pwd);

        if(1 == userService.register(user)){
           return ResultModel.success();
        }else {
            throw new CommonException(ResultCodeEnum.UNKNOWN_ERROR);
        }
    }


    // @formatter:off
    /**
     * @api {post} /api/user/delete 用户删除
     * @apiName delete
     * @apiGroup User
     *
     * @apiParam {int} id 用户id
     *
     * @apiSuccessExample {json} Response 200 Example
     *  {
     *      "code": 200,
     *      "data": null,
     *      "result_msg": "删除成功",
     *      "error_stack_trace": null
     *  }
     */
    // @formatter:on
    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public ResultModel delete(@RequestParam("id") Integer id) {
        userService.delete(id);
        return ResultModel.success();
    }


    // @formatter:off
    /**
     * @api {post} /api/user/list 用户列表
     * @apiName list
     * @apiGroup User
     *
     * @apiSuccessExample {json} Response 200 Example
     *  {
     *      "code": 200,
     *      "data": null,
     *      "result_msg": "获取成功",
     *      "error_stack_trace": null
     *  }
     */
    // @formatter:on
    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ResultModel list() {
        return ResultModel.success(userService.list());
    }


    // @formatter:off
    /**
     * @api {post} /api/user/login 用户登录
     * @apiName login
     * @apiGroup User
     *
     * @apiParam {string} name 用户名称
     * @apiParam {string} pwd 用户密码
     *
     * @apiSuccess (Code) {string} 200 登录成功
     * @apiSuccess (Code) {string} 403 密码错误
     * @apiSuccess (Code) {string} 404 无此用户
     * @apiSuccessExample {json} Response 200 Example
     *  {
     *      "code": 200,
     *      "data": null,
     *      "result_msg": "登录成功",
     *      "error_stack_trace": null
     *  }
     */
    // @formatter:on
    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResultModel<String> login(@RequestParam("name") String name,
                                        @RequestParam("pwd") String pwd,
                                     HttpSession session) {
        User user = new User();
        user.setName(name);
        user.setPwd(pwd);

        user = userService.login(user);

        session.setAttribute(web.loggedUserFlag, true);
        session.setAttribute(web.loggedUserInfo, user);
        return ResultModel.success(null, "登录成功");
    }
}
