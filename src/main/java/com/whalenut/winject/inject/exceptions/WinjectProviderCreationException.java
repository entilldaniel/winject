package com.whalenut.winject.inject.exceptions;

public class WinjectProviderCreationException extends RuntimeException {

    public WinjectProviderCreationException(String message) {
        super(message);
    }

    public WinjectProviderCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
