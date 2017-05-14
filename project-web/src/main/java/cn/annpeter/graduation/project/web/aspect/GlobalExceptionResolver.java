package cn.annpeter.graduation.project.web.aspect;


import cn.annpeter.graduation.project.base.common.exception.CommonException;
import cn.annpeter.graduation.project.base.common.model.ResultCodeEnum;
import cn.annpeter.graduation.project.base.common.model.ResultModel;
import cn.annpeter.graduation.project.core.service.helper.ApplicationExecutorHolder;
import cn.annpeter.graduation.project.core.service.helper.SendEmailService;
import javaslang.control.Try;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static cn.annpeter.graduation.project.core.config.GlobalConfig.email;


/**
 * Created on 2017/03/18
 *
 * @author annpeter.it@gmail.com
 */
public class GlobalExceptionResolver extends SimpleMappingExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionResolver.class);

    @Resource
    private SendEmailService sendEmailService;

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         Object handler, Exception ex) {
        ResultModel result;
        if (ex instanceof CommonException) {
            // CommonException 处理
            CommonException exception = (CommonException) ex;
            result = ResultModel.fail(exception);
            response.setStatus(HttpServletResponse.SC_OK);
        } else if (ex instanceof NoHandlerFoundException) {
            // url 没有找到处理
            NoHandlerFoundException exception = (NoHandlerFoundException) ex;
            result = ResultModel.fail(ResultCodeEnum.RESOURCE_NOT_FOUND, exception.getMessage());
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else if (ex instanceof MissingServletRequestParameterException) {
            // 参数不正确处理
            result = ResultModel.fail(ResultCodeEnum.REQUEST_PARAM_ERROR, "参数类型或数量错误");
            response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
        } else if (ex instanceof ConstraintViolationException) {
            // 方法上的单参数校验错误处理
            ConstraintViolationException exception = (ConstraintViolationException) ex;
            Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();

            // 组装错误消息
            String separator = "; ";
            StringBuilder strBuilder = new StringBuilder();
            for (ConstraintViolation<?> violation : violations) {
                strBuilder.append(violation.getMessage()).append(separator);
            }
            String ret = StringUtils.removeEnd(strBuilder.toString(), separator) + ". ";

            result = ResultModel.fail(ResultCodeEnum.REQUEST_PARAM_ERROR, "参数校验错误" + ret);
            response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
        } else if (ex instanceof MethodArgumentNotValidException) {
            // 对象参数校验错误
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) ex;
            List<ObjectError> errorList = exception.getBindingResult().getAllErrors();

            // 组装错误消息
            String separator = "; ";
            StringBuilder strBuilder = new StringBuilder();
            for (ObjectError error : errorList) {
                strBuilder.append(error.getDefaultMessage()).append(separator);
            }
            String ret = StringUtils.removeEnd(strBuilder.toString(), separator) + ". ";

            result = ResultModel.fail(ResultCodeEnum.REQUEST_PARAM_ERROR, "参数校验错误" + ret);
            response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
        } else if (ex instanceof HttpRequestMethodNotSupportedException) {
            // 请求方式错误处理
            result = ResultModel.fail(ResultCodeEnum.REQUEST_PARAM_ERROR, ex.getMessage());
            response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
        } else {
            // 其它未知异常处理
            result = ResultModel.fail(ResultCodeEnum.UNKNOWN_ERROR, ex);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            String queryString = request.getQueryString();
            String requestURI = request.getRequestURI();
            String sessionId = request.getSession().getId();
            String body = Try.of(() -> IOUtils.toString(request.getInputStream(), request.getCharacterEncoding())).getOrElseGet((e) -> null);
            logger.error("====未知系统异常==== requestURI:{}, queryString:{}, sessionId:{}, body:{}.", requestURI, queryString, sessionId, body, ex);

            if (email.sendSystemErrorEmail) {
                // 发送邮件 异步执行
                ApplicationExecutorHolder.execute("发送系统异常邮件", () -> {
                    Map<String, Object> parameterMap = new HashMap<>();
                    parameterMap.put("requestURI", requestURI);
                    parameterMap.put("queryString", queryString);
                    parameterMap.put("sessionId", sessionId);
                    parameterMap.put("body", body);

                    sendEmailService.sendSystemErrorEmail(parameterMap, ex);
                });
            }
        }

        ModelAndView mav = new ModelAndView();
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        view.setExtractValueFromSingleKeyModel(true);
        mav.setView(view);
        mav.addObject(result);
        return mav;
    }

}