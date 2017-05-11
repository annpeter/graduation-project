package cn.annpeter.graduation.project.web.controller;

import cn.annpeter.graduation.project.base.common.exception.CommonException;
import cn.annpeter.graduation.project.base.common.model.ResultCodeEnum;
import cn.annpeter.graduation.project.base.common.model.ResultModel;
import cn.annpeter.graduation.project.core.service.CountingService;
import cn.annpeter.graduation.project.core.service.UserService;
import cn.annpeter.graduation.project.dal.model.User;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

import static cn.annpeter.graduation.project.core.config.GlobalConfig.web;

/**
 * Created on 2017/03/04
 *
 * @author annpeter.it@gmail.com
 */
@Validated
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private CountingService countingService;


    // @formatter:off
    /**
     * @api {post} /api/user/register 用户注册
     * @apiName register
     * @apiGroup User
     *
     * @apiParam {string} name 用户名称
     * @apiParam {string} pwd 用户密码
     * @apiParam {int} isAdmin 是否为管理员, 0否 1是
     * @apiParam {int} courseId 课程id
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
    @PostMapping(value = "register")
    public ResultModel register(@NotEmpty(message = "name不能为空") String name,
                                @NotEmpty(message = "pwd不能为空") String pwd,
                                @NotNull(message = "isAdmin不能为空") Integer isAdmin,
                                @NotNull(message = "courseId不能为空") Integer courseId) {
        User user = new User();
        user.setName(name);
        user.setPwd(pwd);
        user.setIsAdmin(isAdmin.shortValue());
        user.setCourseId(courseId);

        if (1 == userService.register(user)) {
            return ResultModel.success();
        } else {
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
    @PostMapping(value = "delete")
    public ResultModel delete(@NotNull(message = "用户id不能为空") Integer id) {
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
     *    "code": 200,
     *    "data": [
     *      {
     *        "id": 1,
     *        "name": "曹文浩",
     *        "is_admin": 0,
     *        "course_id": 1,
     *        "create_time": "2017-05-02 09:26:34",
     *        "update_time": "2017-05-02 09:26:57"
     *      },
     *      {
     *        "id": 3,
     *        "name": "蔡智焱",
     *        "is_admin": 0,
     *        "course_id": 1,
     *        "create_time": "2017-05-02 09:26:34",
     *        "update_time": "2017-05-02 09:26:58"
     *      }
     *    ],
     *    "result_msg": "执行成功",
     *    "error_stack_trace": null
     *  }
     */
    // @formatter:on
    @GetMapping(value = "list")
    public ResultModel list(@NotNull(message = "courseId不能为空") Integer courseId) {
        return ResultModel.success(userService.list(courseId));
    }


    // @formatter:off
    /**
     * @api {post} /api/user/login 用户登录
     * @apiName login
     * @apiGroup User
     *
     * @apiParam {string} name 用户名称
     * @apiParam {string} pwd 用户密码
     * @apiParam {int} courseId 课程id
     *
     * @apiSuccess (Code) {string} 200 登录成功
     * @apiSuccess (Code) {string} 403 密码错误
     * @apiSuccess (Code) {string} 404 无此用户
     * @apiSuccessExample {json} Response 200 Example
     *  {
     *    "code": 200,
     *    "data": {
     *      "id": 1,
     *      "name": "曹文浩",
     *      "is_admin": 0,
     *      "course_id": 1,
     *      "create_time": "2017-05-02 09:26:34",
     *      "update_time": "2017-05-02 09:26:57"
     *    },
     *    "result_msg": "登录成功",
     *    "error_stack_trace": null
     *  }
     */
    // @formatter:on
    @PostMapping(value = "login")
    public ResultModel login(@NotEmpty(message = "name不能为空") String name,
                             @NotEmpty(message = "pwd不能为空") String pwd,
                             @NotNull(message = "courseId不能为空") Integer courseId,
                             HttpSession session) {
        // 如果已经登录过了, 就不必重复登录了
        Object isLogin = session.getAttribute(web.loggedUserFlag);
        if (isLogin != null && (boolean) isLogin) {
            User user = (User) session.getAttribute(web.loggedUserInfo);
            return ResultModel.success(user, "登录成功");
        }

        User user = new User();
        user.setName(name);
        user.setPwd(pwd);
        user.setCourseId(courseId);

        user = userService.login(user);
        countingService.incrementUserViewCount();

        session.setAttribute(web.loggedUserFlag, true);
        session.setAttribute(web.loggedUserInfo, user);
        return ResultModel.success(user, "登录成功");
    }


    // @formatter:off
    /**
     * @api {post} /api/user/logout 用户退出登录
     * @apiName logout
     * @apiGroup User
     *
     * @apiSuccessExample {json} Response 200 Example
     *  {
     *      "code": 200,
     *      "data": null,
     *      "result_msg": "登出成功",
     *      "error_stack_trace": null
     *  }
     */
    // @formatter:on
    @GetMapping(value = "logout")
    public ResultModel logout(HttpSession session) {
        session.invalidate();
        return ResultModel.success(null, "登出成功");
    }

    // @formatter:off
    /**
     * @api {post} /api/user/changePwd 修改用户密码
     * @apiName changePwd
     * @apiGroup User
     *
     *  @apiParam {int} userId 用户id，可以为空，即当用户id为空的时候修改自己的密码
     *
     * @apiSuccessExample {json} Response 200 Example
     *  {
     *      "code": 200,
     *      "data": null,
     *      "result_msg": "执行成功",
     *      "error_stack_trace": null
     *  }
     */
    // @formatter:on
    @GetMapping(value = "changePwd")
    public ResultModel changePwd(@NotEmpty(message = "pwd不能为空") String pwd,
                                 Integer userId,
                                 HttpSession session) {
        User sessionUser = (User) session.getAttribute(web.loggedUserInfo);
        User user = new User();
        user.setPwd(pwd);
        user.setId(userId == null ? sessionUser.getId() : userId);
        userService.updateUser(user);
        return ResultModel.success(null, "登出成功");
    }
}
