package cn.annpeter.graduation.project.base.mybatis.page.exception;

/**
 * Created on 2017/02/19
 *
 * @author annpeter.it@gmail.com
 */
public class DBTypeCanNotBeNullException extends RuntimeException {
    public DBTypeCanNotBeNullException() {
        super();
    }

    public DBTypeCanNotBeNullException(String message) {
        super(message);
    }
}
