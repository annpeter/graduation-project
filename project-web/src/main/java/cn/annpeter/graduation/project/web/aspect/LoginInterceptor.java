package cn.annpeter.graduation.project.web.aspect;


import cn.annpeter.graduation.project.base.common.model.ResultModel;
import cn.annpeter.graduation.project.base.common.util.JsonUtils;
import cn.annpeter.graduation.project.dal.model.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static cn.annpeter.graduation.project.base.common.model.ResultCodeEnum.USER_NOT_LOGIN;
import static cn.annpeter.graduation.project.core.config.GlobalConfig.web;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

public class LoginInterceptor implements HandlerInterceptor {

    private String userNotLoginMsg = JsonUtils.object2String(ResultModel.fail(USER_NOT_LOGIN));

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        HttpSession session = request.getSession();

        //session.setAttribute(web.loggedUserFlag, true);
        //User user = new User();
        //user.setId(1);
        //user.setIsAdmin((short) 0);
        //session.setAttribute(web.loggedUserInfo, user);

        Object isLogin = session.getAttribute(web.loggedUserFlag);
        if (isLogin != null && (boolean) isLogin) {
            return true;
        }

        response.setStatus(200);
        response.setContentType(APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().append(userNotLoginMsg);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
    }
}

