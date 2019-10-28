package com.docletinterface.doclet.exception;

public class DocumentException extends RuntimeException {

    public DocumentException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
