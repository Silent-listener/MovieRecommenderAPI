package com.stackroute.movieservice.exceptions;

public class EmptyDbException extends Exception {
    private String message;

    public EmptyDbException() {
    }

    public EmptyDbException(String message) {
        super(message);
        this.message = message;
    }
}
