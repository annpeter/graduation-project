package cn.annpeter.graduation.project.web.aspect;


import cn.annpeter.graduation.project.core.common.exception.ComExp;
import cn.annpeter.graduation.project.core.common.exception.ComResEnum;
import cn.annpeter.graduation.project.web.model.ResultModel;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import java.util.Arrays;

/**
 * 结果 Aop
 * Created by liupeng on 14-7-8 at 下午10:21.
 */
@Aspect
@Named
public class ResultAspect {

    private static final Logger logger = LoggerFactory.getLogger(ResultAspect.class);

    @Around("execution(* cn.annpeter.graduation.project.web.controller.*.*(..))")
    public Object doAroundMethod(ProceedingJoinPoint pig) throws Throwable {
        MethodSignature methodSign = (MethodSignature) pig.getSignature();

        Object obj;
        try {
            long startTime = System.currentTimeMillis();
            logger.info("开始执行{}, 参数名称{}, 值{}.", methodSign.getMethod().getName(),
                    Arrays.toString(methodSign.getParameterNames()), Arrays.toString(pig.getArgs()));
            // 执行controller
            obj = pig.proceed();
            logger.info("结束执行{}, 总耗时{}ms.",
                    methodSign.getMethod().getName(), System.currentTimeMillis() - startTime);
        } catch (ComExp ex) {
            logger.error("执行{}出错, 参数名称{}, 值{}.", methodSign.getMethod().getName(),
                    Arrays.toString(methodSign.getParameterNames()), Arrays.toString(pig.getArgs()), ex);

            // 构造返回对象
            Class<?> returnTypeMethod = methodSign.getReturnType();
            Object resultObj = returnTypeMethod.newInstance();
            if (resultObj instanceof ResultModel) {
                ResultModel<?> result = (ResultModel<?>) resultObj;
                result.setCode(ex.getError().getCode());
                result.setResultMsg(ex.getErrMsg());
                result.setErrorStackTrace(ExceptionUtils.getRootCauseMessage(ex));
                return result;
            } else {
                throw ex;
            }
        } catch (Exception ex) {
            logger.error("执行{}出错, 参数名称{}, 值{}.", methodSign.getMethod().getName(),
                    Arrays.toString(methodSign.getParameterNames()), Arrays.toString(pig.getArgs()), ex);

            // 构造返回对象
            Class<?> returnTypeMethod = methodSign.getReturnType();
            Object resultObj = returnTypeMethod.newInstance();
            if (resultObj instanceof ResultModel) {
                ResultModel<?> result = (ResultModel<?>) resultObj;
                result.setCode(ComResEnum.UNKNOWN_ERROR.getCode());
                result.setResultMsg(ex.getMessage());

                result.setErrorStackTrace(ExceptionUtils.getStackTrace(ex));
                return result;
            } else {
                throw ex;
            }
        }
        return obj;
    }

}
