package cn.annpeter.graduation.project.base.common.exception;

/**
 * Created on 2017/02/20
 *
 * @author annpeter.it@gmail.com
 */
public class CrackErrorException extends RuntimeException {

    public CrackErrorException(String message) {
        super(message);
    }

    public CrackErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
