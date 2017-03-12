package cn.annpeter.graduation.project.web.interceptor;


import cn.annpeter.graduation.project.dal.model.User;
import cn.annpeter.graduation.project.web.model.WebConstants;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static cn.annpeter.graduation.project.core.common.exception.ComResEnum.FORBIDDEN;


public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        if (true) {
            HttpSession session = request.getSession();
            User user = new User();
            user.setId(1);
            user.setName("annpeter");
            user.setPwd("annpeter");

            session.setAttribute(WebConstants.LOGIN_USER_FLAG, true);
            session.setAttribute(WebConstants.LOGIN_USER_INFO, user);
        }

        HttpSession session = request.getSession();
        Object isLogin = session.getAttribute(WebConstants.LOGIN_USER_FLAG);
        if (isLogin != null && (boolean) isLogin) {
            return true;
        }

        response.setStatus(200);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().append("{\"code\": ").append(String.valueOf(FORBIDDEN.getCode()))
                .append(", \"data\": \"用户未登录\"}")
                .append(", \"result_msg\": \"成功\"}")
                .append(", \"error_stack_trace\": null}");
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

