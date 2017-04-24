package cn.annpeter.graduation.project.base.common.exception;

/**
 * Created on 2017/03/20
 *
 * @author annpeter.it@gmail.com
 */
public class TryFailureException extends Exception {
    public TryFailureException(Throwable cause) {
        super(cause);
    }

    public TryFailureException(String message) {
        super(message);
    }
}