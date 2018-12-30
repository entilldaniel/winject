package com.whalenut.winject.inject.exceptions;

public class WinjectException extends RuntimeException {

    public WinjectException(String message) {
        super(message);
    }

    public WinjectException(String message, Throwable cause) {
        super(message, cause);
    }
}
