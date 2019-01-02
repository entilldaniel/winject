package com.whalenut.winject.inject.exceptions;

public class WinjectSetterException extends WinjectException {

    public WinjectSetterException(String message) {
        super(message);
    }

    public WinjectSetterException(String message, Throwable cause) {
        super(message, cause);
    }
}
