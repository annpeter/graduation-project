package cn.annpeter.graduation.project.base.common.exception;

/**
 * Created on 2017/02/20
 *
 * @author annpeter.it@gmail.com
 */
public class SerializationErrorException extends RuntimeException {
    public SerializationErrorException(String message) {
        super(message);
    }

    public SerializationErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
