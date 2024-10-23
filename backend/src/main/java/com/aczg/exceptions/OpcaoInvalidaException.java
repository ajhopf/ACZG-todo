package com.aczg.exceptions;

public class OpcaoInvalidaException extends Exception {
    private String message;

    public OpcaoInvalidaException(String message) {
        super(message);
    }
}
