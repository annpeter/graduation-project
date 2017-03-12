package cn.annpeter.graduation.project.base.mybatis.page.exception;

/**
 * Created on 2017/02/19
 *
 * @author annpeter.it@gmail.com
 */
public class UnSupportedDBTypeException extends RuntimeException {
    public UnSupportedDBTypeException() {
        super();
    }

    public UnSupportedDBTypeException(String message) {
        super(message);
    }
}
