package cn.annpeter.graduation.project.web.controller;

import cn.annpeter.graduation.project.core.common.exception.ComExp;
import cn.annpeter.graduation.project.core.common.exception.ComResEnum;
import cn.annpeter.graduation.project.core.service.UserService;
import cn.annpeter.graduation.project.dal.model.User;
import cn.annpeter.graduation.project.web.model.ResultModel;
import cn.annpeter.graduation.project.web.model.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
     *      "data": "注册成功",
     *      "result_msg": "执行成功",
     *      "error_stack_trace": null
     *  }
     */
    @ResponseBody
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ResultModel<String> register(@RequestParam("name") String name,
                                        @RequestParam("pwd") String pwd) {
        User user = new User();
        user.setName(name);
        user.setPwd(pwd);

        if(1 == userService.register(user)){
           return ResultModel.success("注册成功");
        }else {
            throw new ComExp(ComResEnum.UNKNOWN_ERROR);
        }
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
     *      "data": "登录成功",
     *      "result_msg": "执行成功",
     *      "error_stack_trace": null
     *  }
     */
    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResultModel<String> login(@RequestParam("name") String name,
                                        @RequestParam("pwd") String pwd,
                                     HttpSession session) {
        User user = new User();
        user.setName(name);
        user.setPwd(pwd);

        user = userService.login(user);
        session.setAttribute(WebConstants.LOGIN_FLAG, true);
        session.setAttribute(WebConstants.LOGIN_INFO, user);
        return ResultModel.success("登录成功");
    }
}
