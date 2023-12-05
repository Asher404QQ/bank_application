package ru.kors.exception;

public class UserIsAlreadyExistsException extends Exception{
    public UserIsAlreadyExistsException() {
    }

    public UserIsAlreadyExistsException(String message) {
        super(message);
    }

    public UserIsAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserIsAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public UserIsAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
