package cn.annpeter.graduation.project.base.common.exception;


import cn.annpeter.graduation.project.base.common.model.ResultCodeEnum;

/**
 * Created on 2017/03/10
 *
 * @author annpeter.it@gmail.com
 */
public class CommonException extends RuntimeException {

    private String errMsg;
    private String detailMsg;
    private ResultCodeEnum error;

    public CommonException(ResultCodeEnum error) {
        super(error.getMsg());
        this.error = error;
        this.errMsg = error.getMsg();
    }

    public CommonException(ResultCodeEnum error, String errMsg) {
        super(errMsg);
        this.error = error;
        this.errMsg = errMsg;
    }

    public CommonException(ResultCodeEnum error, String errMsg, String detailMsg) {
        super(detailMsg);
        this.error = error;
        this.errMsg = errMsg;
        this.detailMsg = detailMsg;
    }

    public CommonException(ResultCodeEnum error, String errMsg, Throwable cause) {
        super(errMsg, cause);
        this.error = error;
        this.errMsg = errMsg;
    }

    public CommonException(ResultCodeEnum error, String errMsg, String detailMsg, Throwable cause) {
        super(detailMsg, cause);
        this.error = error;
        this.errMsg = errMsg;
        this.detailMsg = detailMsg;
    }

    public CommonException(ResultCodeEnum error, Throwable cause) {
        super(error.getMsg(), cause);
        this.error = error;
        this.errMsg = error.getMsg();
    }

    public String getErrMsg() {
        return errMsg;
    }

    public ResultCodeEnum getError() {
        return error;
    }

    public String getDetailMsg() {
        return detailMsg;
    }

    public void setDetailMsg(String detailMsg) {
        this.detailMsg = detailMsg;
    }
}
