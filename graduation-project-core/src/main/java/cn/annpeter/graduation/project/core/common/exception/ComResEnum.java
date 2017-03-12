package cn.annpeter.graduation.project.core.common.exception;

/**
 * Created on 2017/03/10
 *
 * @author annpeter.it@gmail.com
 */
public enum ComResEnum {

    SUCCESS(200, "执行成功"),
    FORBIDDEN(403, "权限不足"),
    RESOURCE_NOT_FOUND(404, "资源未找到"),
    RESOURCE_CONFLICT(409, "资源冲突"),
    UNKNOWN_ERROR(500, "未知错误");


    private Integer code;
    private String msg;

    ComResEnum(Integer code, String msg) {
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
