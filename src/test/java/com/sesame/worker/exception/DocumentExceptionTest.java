package com.docletinterface.doclet.exception;

import org.junit.Test;

//TODO: Check how can test exceptions
public class DocumentExceptionTest {

    @Test(expected = DocumentException.class)
    public void testDocumentExceptionTest() {

        RuntimeException ex = new RuntimeException("Valid runtime exception!");
        throw new DocumentException("Test exception!", ex);

    }

}
