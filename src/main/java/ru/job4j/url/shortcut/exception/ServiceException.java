package ru.job4j.url.shortcut.exception;

public class ServiceException extends Exception {
    public ServiceException(String message, Exception cause) {
        super(message, cause);
    }
}
