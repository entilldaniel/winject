package com.whalenut.winject.inject.exceptions;

/**
 * Exception indicating that Winject could not instantiate the requested
 * class.
 */
public class WinjectInstantiationException extends WinjectException {

    public WinjectInstantiationException(String message) {
        super(message);
    }

    public WinjectInstantiationException(String message, Throwable cause) {
        super(message, cause);
    }
}
