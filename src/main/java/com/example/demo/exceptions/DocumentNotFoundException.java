package com.example.demo.exceptions;

public class DocumentNotFoundException extends RuntimeException {
    public DocumentNotFoundException(String msg) {
        super(msg);
    }
}
