package cn.annpeter.graduation.project.base.common.model;

/**
 * Created on 2017/03/10
 *
 * @author annpeter.it@gmail.com
 */
public enum ResultCodeEnum {
    SUCCESS(200, "执行成功"),
    UPLOAD_OSS_ERROR(300, "上传OSS失败"),
    USER_NOT_LOGIN(400, "用户未登录"),
    FORBIDDEN(403, "权限不足"),
    RESOURCE_NOT_FOUND(404, "资源未找到"),
    RESOURCE_CONFLICT(409, "资源冲突"),
    REQUEST_PARAM_ERROR(412, "参数错误"),
    PRECONDITION_FAILED(428, "要求先决条件"),
    UNKNOWN_ERROR(500, "未知错误"),
    SERVER_POWER_LESS(501, "服务器无法完成该请求");

    private Integer code;
    private String msg;

    ResultCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
