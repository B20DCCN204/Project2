package com.javaweb.springbootnonjwt.controlleradvice;

public class MathematicException extends RuntimeException{
    public MathematicException(String message) {
        super(message);
    }
}
