package cn.annpeter.graduation.project.base.restful.rpc.client;

import cn.annpeter.graduation.project.base.restful.rpc.utils.HttpConnectionUtils;
import cn.annpeter.graduation.project.base.restful.rpc.utils.CustomObjectMapperHelper;
import cn.annpeter.graduation.project.base.restful.rpc.utils.HttpSignCalculator;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2017/02/20
 * Http执行器.
 * 对接口声明进行扫描,确定需要访问的地址和参数组装方式.
 * 调用Http 调用接口.
 *
 * @author annpeter.it@gmail.com
 */
public class HttpProcessor {

    private Class<?> targetClass;
    private String endPoint;

    private String signName;
    private boolean needSign;
    private String signKey;
    private String systemCode;
    private String methodFix = "";
    private Integer timeOut = 5000;     //超时时间设置

    public HttpProcessor(Class<?> targetClass, String endPoint, String signName, boolean needSign,
                         String signKey, String systemCode, String methodFix) {
        this.targetClass = targetClass;
        this.endPoint = endPoint;
        this.signKey = signKey;
        this.signName = signName;
        this.needSign = needSign;
        this.systemCode = systemCode;
        if (methodFix != null && methodFix.trim().length() > 0) {
            this.methodFix = methodFix;
        }
    }

    public HttpProcessor(Class<?> targetClass, String endPoint, String signName, boolean needSign,
                         String signKey, String systemCode, String methodFix, Integer timeOut) {
        this(targetClass, endPoint, signName, needSign, signKey, systemCode, methodFix);
        if (timeOut != null && timeOut > 0) {
            this.setTimeOut(timeOut);
        }
    }

    /**
     * 处理请求.
     *
     * @param method 调用的方法.
     * @param params 调用方法的参数.
     */
    public Object process(Method method, Object[] params) throws Exception {
        if (method == null) {
            throw new Exception("the method is null , please check the " + targetClass + " version.");
        }

        String mediaType = MediaType.APPLICATION_FORM_URLENCODED;
        if (targetClass.isAnnotationPresent(Consumes.class)) {
            Consumes consume = targetClass.getAnnotation(Consumes.class);
            if (consume != null && consume.value().length > 0) {
                mediaType = consume.value()[0];
            }
        }

        // 默认使用POST方法提交
        String restfulMethod = HttpMethod.POST;
        if (method.isAnnotationPresent(HttpMethod.class)) {
            //如果方法上存在Method的注解,使用注解.
            HttpMethod httpMethod = method.getAnnotation(HttpMethod.class);
            restfulMethod = httpMethod.value();
        }
        if (method.isAnnotationPresent(Consumes.class)) {
            Consumes consume = method.getAnnotation(Consumes.class);
            if (consume != null && consume.value().length > 0) {
                mediaType = consume.value()[0];
            }
        }

        if (params != null) {
            Map<String, Object> bodyParams = new HashMap<>();
            Map<String, String> queryParams = new HashMap<>();

            // 寻找方法上的所有参数的注解.
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();

            for (int i = 0; i < params.length; i++) {
                Annotation[] parameterAnnotation = parameterAnnotations[i];
                String paramName = null;

                Object paramValue = params[i];
                QueryParam queryParamAnnotation = findAnnotation(parameterAnnotation, QueryParam.class);
                if (queryParamAnnotation != null) {
                    paramName = queryParamAnnotation.value();
                    queryParams.put(paramName, parameterValue(paramValue));
                    continue;
                }

                FormParam formParamAnnotation = findAnnotation(parameterAnnotation, FormParam.class);
                if (formParamAnnotation != null) {
                    paramName = formParamAnnotation.value();
                    bodyParams.put(paramName, parameterValue(paramValue));
                } else {
                    bodyParams.clear();
                    bodyParams.put(paramName, paramValue);
                }
            }

            // 获取类和方法上的URI
            String uri = getUri(method);

            // 生成最终的访问请求URL
            String requestUrl = joinUrl(this.endPoint, uri);

            // 接口签名写在Http请求的head里面.
            Map<String, String> headerMap = new HashMap<>();
            if (needSign) {
                String sign = HttpSignCalculator.calculateSign(bodyParams, queryParams, this.signKey);
                headerMap.put(signName, sign);
                headerMap.put("systemCode", systemCode);
            }
            byte[] json = HttpConnectionUtils.loadJson(requestUrl, queryParams, bodyParams, headerMap,
                    restfulMethod, mediaType, timeOut);

            return convertResult(json, method);
        }
        return null;
    }

    /**
     * 将当前的json返回method的返回值对象
     */
    private Object convertResult(byte[] json, Method method) {
        return CustomObjectMapperHelper.parseBytes2Object(json, method.getReturnType());
    }

    private <T> T findAnnotation(Annotation[] annotationArray, Class<T> clazz) {
        if (annotationArray != null) {
            for (Annotation an : annotationArray) {
                if (clazz.isAssignableFrom(an.annotationType())) {
                    return (T) an;
                }
            }
        }
        return null;
    }

    private String parameterValue(Object paramValue) {
        if (paramValue == null) {
            return "";
        } else if (paramValue.getClass().isArray() || paramValue instanceof Collection) {
            Object[] array;
            if (paramValue.getClass().isArray()) {
                array = (Object[]) paramValue;
            } else {
                Collection<Object> collection = (Collection) paramValue;
                array = collection.toArray(new Object[0]);
            }
            StringBuilder sb = new StringBuilder();
            for (Object item : array) {
                if (sb.length() <= 0) {
                    sb.append(item);
                } else {
                    sb.append(",").append(item);
                }
            }
            return sb.toString();
        } else {
            return paramValue + "";
        }
    }

    /**
     * 获取方法上或者类上的URI路径
     *
     * @param element 类对象或者方法对象
     */
    private String getMethodAndClassPath(AnnotatedElement element) {
        if (element.isAnnotationPresent(Path.class)) {
            Path resource = element.getAnnotation(Path.class);
            // 如果方法上存在Resource的注解申明, 使用注解.
            return resource.value();
        }

        if (element.isAnnotationPresent(RequestMapping.class)) {
            RequestMapping requestMapping = element.getAnnotation(RequestMapping.class);
            // 如果方法上存在RequestMapping的注解申明, 使用注解.
            if (requestMapping.path().length > 0) {
                return requestMapping.path()[0];
            }
            if (requestMapping.value().length > 0) {
                return requestMapping.value()[0];
            }
        }
        return null;
    }

    /**
     * 获取完整的URI
     */
    private String getUri(Method method) {
        String url = "";

        // 获取类上面的url路径
        String classPath = getMethodAndClassPath(targetClass);
        if (!StringUtils.isEmpty(classPath)) {
            url = joinUrl(url, classPath);
        }

        String methodUrl = method.getName();
        String methodPath = getMethodAndClassPath(method);
        if (!StringUtils.isEmpty(methodPath)) {
            methodUrl = methodPath;
        }

        return joinUrl(url, methodUrl + this.methodFix);
    }


    /**
     * 2个url相加 去掉中间多余的"/"
     */
    private String joinUrl(String firstUrl, String secondUrl) {
        StringBuilder sb = new StringBuilder(firstUrl);
        if (firstUrl.endsWith("/")) {
            if (secondUrl.startsWith("/")) {
                sb.append(secondUrl.substring(1));
            } else {
                sb.append(secondUrl);
            }
        } else {
            if (secondUrl.startsWith("/")) {
                sb.append(secondUrl);
            } else {
                sb.append("/").append(secondUrl);
            }
        }
        return sb.toString();
    }

    public void setTimeOut(Integer timeOut) {
        this.timeOut = timeOut;
    }
}
