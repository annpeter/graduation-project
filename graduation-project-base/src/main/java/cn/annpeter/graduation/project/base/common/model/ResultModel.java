package cn.annpeter.graduation.project.base.common.model;


import cn.annpeter.graduation.project.base.common.exception.CommonException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static cn.annpeter.graduation.project.base.common.model.ResultCodeEnum.SUCCESS;


/**
 * ResultModel 返回结果类
 * Created by tianjin.lp on 16/8/31.
 */
public class ResultModel<T> {

    private int code = SUCCESS.getCode();
    private T data;
    private String resultMsg = SUCCESS.getMsg();
    private String errorStackTrace;

    private static ResultModel SUCCESS_RESULT_MODEL = ResultModel.success(null);

    public static ResultModel success() {
        return SUCCESS_RESULT_MODEL;
    }

    public static <T> ResultModel<T> success(T data) {
        return success(data, SUCCESS.getMsg());
    }

    public static <T> ResultModel<T> success(T data, String msg) {
        return new ResultModel<>(SUCCESS.getCode(), data, msg, null);
    }

    public static <T> ResultModel<T> fail(CommonException ex) {
        String resultMsg = ex.getError().getMsg();
        if (StringUtils.isNotEmpty(ex.getErrMsg())) {
            resultMsg = ex.getErrMsg();
        }
        return new ResultModel<>(ex.getError().getCode(), null, resultMsg, ExceptionUtils.getStackTrace(ex));
    }

    public static <T> ResultModel<T> fail(ResultCodeEnum error) {
        return new ResultModel<>(error.getCode(), null, error.getMsg(), null);
    }

    public static <T> ResultModel<T> fail(ResultCodeEnum error, String msg) {
        return new ResultModel<>(error.getCode(), null, msg, null);
    }

    public static <T> ResultModel<T> fail(ResultCodeEnum error, Exception ex) {
        return new ResultModel<>(error.getCode(), null, error.getMsg(), ExceptionUtils.getStackTrace(ex));
    }

    public static <T> ResultModel<T> fail(ResultCodeEnum error, String msg, Exception ex) {
        return new ResultModel<>(error.getCode(), null, msg, ExceptionUtils.getStackTrace(ex));
    }

    public ResultModel(int code, T data, String resultMsg, String errorStackTrace) {
        this.code = code;
        this.data = data;
        this.resultMsg = resultMsg;
        this.errorStackTrace = errorStackTrace;
    }

    public int getCode() {
        return code;
    }

    public String getErrorStackTrace() {
        return errorStackTrace;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public T getData() {
        return data;
    }

}
