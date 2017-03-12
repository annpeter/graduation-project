package cn.annpeter.graduation.project.base.restful.rpc.utils;


import cn.annpeter.graduation.project.base.restful.rpc.client.MethodInvoke;

/**
 * Created on 2017/02/20
 * 直接在代码里面创建一个接口访问对象.
 * 直接使用接口函数访问远程接口.
 *
 * @author annpeter.it@gmail.com
 */
public abstract class HttpServiceClient {

    /**
     * 创建一个httpService的接口客户端
     */
    public static <T> T createClient(Class<T> clazz, String endPoint, String signName,
                                     boolean needSign, String signKey, String systemCode,
                                     String methodFix) throws Exception {
        MethodInvoke invoker = new MethodInvoke();
        invoker.setEndPoint(endPoint);
        invoker.setMethodFix(methodFix);
        invoker.setNeedSign(needSign);
        invoker.setSignKey(signKey);
        invoker.setSignName(signName);
        invoker.setSystemCode(systemCode);
        invoker.setTargetClass(clazz);
        return (T) invoker.create();
    }
}
