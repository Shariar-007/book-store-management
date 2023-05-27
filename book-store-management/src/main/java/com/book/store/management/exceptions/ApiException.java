package com.book.store.management.exceptions;

public class ApiException extends RuntimeException{
    public ApiException(String message) {
        super(message);
    }

    public ApiException() {
        super();
    }
}
