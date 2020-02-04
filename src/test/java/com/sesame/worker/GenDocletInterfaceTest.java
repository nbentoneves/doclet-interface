package com.sesame.worker;

import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class GenDocletInterfaceTest {

    private DocumentationWorker<String> javaDocsDocumentationWorker = new JavaDocsDocumentationWorkerImpl();

    @Mock
    private DocumentationFactory documentationFactory;

    @Test
    public void testWhenRootDocIsNull() {
        when(documentationFactory.getJavaDocsDocumentationWorker()).thenReturn(javaDocsDocumentationWorker);
        assertFalse(new GenDocletInterface(documentationFactory).start(null));
    }

    @Test
    public void testWhenRootDocIsValid() {

        when(documentationFactory.getJavaDocsDocumentationWorker()).thenReturn(javaDocsDocumentationWorker);
        GenDocletInterface genDocletInterface = new GenDocletInterface(documentationFactory);

        String rawCommentTest = "\n" +
                "Test javadoc\n" +
                "\n" +
                "<pre>\n" +
                "@doclib\n" +
                "\n" +
                "className: TestServiceClass\n" +
                "packageName: com.docletinterface\n" +
                "* methodName: calculator\n" +
                "* methodDescription: It's a service to calculate two numbers\n" +
                "* return: Int - the result of calculation\n" +
                "* param-1: Int - The first number - Simple description for the first parameter\n" +
                "* param-2: Int - The second number - Simple description for the second parameter\n" +
                "*\n" +
                "* @enddoclib\n" +
                "* </pre>\n" +
                "*/";

        assertTrue(genDocletInterface.start(rawCommentTest));
        assertNotNull(genDocletInterface.getDocMethod());

    }

}
