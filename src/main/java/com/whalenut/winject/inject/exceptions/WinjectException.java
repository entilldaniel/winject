package com.whalenut.winject.inject.exceptions;

/**
 * Generic Winject exception for unhandled cases.
 */
public class WinjectException extends RuntimeException {

    public WinjectException(String message) {
        super(message);
    }

    public WinjectException(String message, Throwable cause) {
        super(message, cause);
    }
}
