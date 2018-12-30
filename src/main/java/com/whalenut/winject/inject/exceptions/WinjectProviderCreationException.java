package com.whalenut.winject.inject.exceptions;

/**
 * Exceptions related to creating providers.
 */
public class WinjectProviderCreationException extends WinjectException {

    public WinjectProviderCreationException(String message) {
        super(message);
    }

    public WinjectProviderCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
