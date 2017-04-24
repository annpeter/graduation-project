package cn.annpeter.graduation.project.base.common.annotation;

import java.lang.annotation.*;

/**
 * Created on 2017/04/08
 *
 * @author annpeter.it@gmail.com
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public  @interface RemoteServiceLog {
    String desc()  default "";
}