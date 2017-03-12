package cn.annpeter.graduation.project.core.common.exception;

/**
 * Created on 2017/03/10
 *
 * @author annpeter.it@gmail.com
 */
public class ComExp extends RuntimeException {

    private String errMsg;
    private ComResEnum error;

    public ComExp(ComResEnum error) {
        super(error.getMsg());
        this.error = error;
        this.errMsg = error.getMsg();
    }

    public ComExp(ComResEnum error, String errMsg) {
        super(errMsg);
        this.error = error;
        this.errMsg = errMsg;
    }

    public ComExp(ComResEnum error, String errMsg, Throwable cause) {
        super(errMsg, cause);
        this.error = error;
        this.errMsg = errMsg;
    }

    public ComExp(ComResEnum error, Throwable cause) {
        super(error.getMsg(), cause);
        this.error = error;
        this.errMsg = error.getMsg();
    }

    public ComExp setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public ComResEnum getError() {
        return error;
    }

}
