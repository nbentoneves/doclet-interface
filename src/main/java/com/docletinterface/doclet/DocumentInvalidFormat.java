package com.docletinterface.doclet;

public class DocumentInvalidFormat extends RuntimeException {

    public DocumentInvalidFormat(String message, Throwable throwable) {
        super(message, throwable);
    }

}
