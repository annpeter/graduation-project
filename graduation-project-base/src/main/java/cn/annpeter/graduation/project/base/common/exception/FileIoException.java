package cn.annpeter.graduation.project.base.common.exception;

/**
 * Created on 2016/09/28
 *
 * @author annpeter.it@gmail.com
 */
public class FileIoException extends RuntimeException {
    public FileIoException(String message) {
        super(message);
    }

    public FileIoException(String message, Throwable cause) {
        super(message, cause);
    }
}