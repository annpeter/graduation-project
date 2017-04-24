package cn.annpeter.graduation.project.web.aspect;


import org.apache.commons.lang3.RandomStringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created on 2017/03/16
 *
 * @author annpeter.it@gmail.com
 */
@Aspect
@Component
public class ControllerLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(ControllerLogAspect.class);

    @Around("execution(* com.indata.xcard.service.web.controller.*Controller.*(..))")
    public Object doAroundMethod(ProceedingJoinPoint pig) throws Throwable {

        MDC.put("request-id", RandomStringUtils.randomAlphabetic(10));
        MethodSignature methodSign = (MethodSignature) pig.getSignature();
        Class targetClass = pig.getTarget().getClass();
        String methodSignName = methodSign.getMethod().getName();
        String targetClassName = targetClass.getSimpleName();

        Object obj;
        try {
            long startTime = System.currentTimeMillis();
            logger.info("{}执行{}开始, 参数名称{}, 值{}.", targetClassName, methodSignName,
                    Arrays.toString(methodSign.getParameterNames()), Arrays.toString(pig.getArgs()));
            // 执行controller
            obj = pig.proceed();
            logger.info("{}执行{}结束, 总耗时{}ms.", targetClassName, methodSignName, System.currentTimeMillis() - startTime);
        } catch (Throwable e) {
            logger.error("{}执行{}出错, 参数名称{}, 值{}.", targetClassName, methodSignName,
                    Arrays.toString(methodSign.getParameterNames()), Arrays.toString(pig.getArgs()), e);
            throw e;
        } finally {
            MDC.clear();
        }
        return obj;
    }

}
