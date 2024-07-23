package ru.job4j.url.shortcut.exception;

public class ControllerException extends Exception {
    public ControllerException(String message, Exception cause) {
        super(message, cause);
    }
}
