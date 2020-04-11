package com.sesame.worker.exception;

import org.junit.Test;

//TODO: Check how can test exceptions
public class DocumentInvalidFormatExceptionTest {

    @Test(expected = DocumentException.class)
    public void testDocumentInvalidFormatExceptionTest() {

        RuntimeException ex = new RuntimeException("Valid runtime exception!");
        throw new DocumentInvalidFormatException("Test exception!", ex);

    }

}
