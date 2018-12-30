package com.whalenut.winject.inject.exceptions;

public class WinjectInstantiationException extends RuntimeException {

    public WinjectInstantiationException() { }

    public WinjectInstantiationException(String message) {
        super(message);
    }

    public WinjectInstantiationException(String message, Throwable cause) {
        super(message, cause);
    }
}
