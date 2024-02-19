package com.example.emp.exception;

public class InvalidPageNumberException extends RuntimeException{
    public InvalidPageNumberException(String message) {
        super(message);
    }
}
