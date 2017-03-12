package cn.annpeter.graduation.project.web.model;


import cn.annpeter.graduation.project.core.common.exception.ComResEnum;

/**
 * ResultModel 返回结果类
 * Created by tianjin.lp on 16/8/31.
 */
public class ResultModel<T> {

    private int code = ComResEnum.SUCCESS.getCode();

    private T data;

    private String resultMsg = ComResEnum.SUCCESS.getMsg();

    private String errorStackTrace;

    public static <T> ResultModel<T> success(T data) {
        return success(data, ComResEnum.SUCCESS.getMsg());
    }

    public static <T> ResultModel<T> success(T data, String msg) {
        ResultModel<T> res = new ResultModel<T>();
        res.setCode(ComResEnum.SUCCESS.getCode());
        res.setData(data);
        res.setResultMsg(msg);
        return res;
    }

    public static <T> ResultModel<T> fail(ComResEnum errors, String msg) {
        ResultModel<T> res = new ResultModel<T>();
        res.setCode(errors.getCode());
        res.setData(null);

        res.setResultMsg(msg);
        return res;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorStackTrace() {
        return errorStackTrace;
    }

    public void setErrorStackTrace(String errorStackTrace) {
        this.errorStackTrace = errorStackTrace;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
