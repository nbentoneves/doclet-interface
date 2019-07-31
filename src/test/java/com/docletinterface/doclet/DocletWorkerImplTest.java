package com.docletinterface.doclet;

import com.docletinterface.domain.DocMethod;
import com.sun.javadoc.MethodDoc;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class DocletWorkerImplTest {

    private DocletWorker victim;

    @Mock
    public MethodDoc methodDoc;

    @Before
    public void setUp() {
        initMocks(this);
        victim = new DocletWorkerImpl();
    }

    @After
    public void cleanUp() {
        reset(methodDoc);
    }

    @Test
    public void testExtractEmptyRawCommentValidDocMethod() {

        when(methodDoc.getRawCommentText()).thenReturn("");
        assertFalse(victim.processInterfaceMethod(methodDoc).isPresent());

    }

    @Test
    public void testExtractInvalidDocMethod() {

        when(methodDoc.getRawCommentText()).thenReturn(null);
        assertFalse(victim.processInterfaceMethod(methodDoc).isPresent());

    }

    @Test
    public void testExtractNullDocMethod() {

        assertFalse(victim.processInterfaceMethod(null).isPresent());

    }

    @Test
    public void testExtractDocumentationWithoutPrefix() {

        String documentation = "\n" +
                "methodName: startMethod\n" +
                "methodDescription: Description\n" +
                "return: String - Description\n" +
                "param-1: Int - Description\n" +
                "param-2: XptoObject - Description\n" +
                "@enddoclib ";

        when(methodDoc.getRawCommentText()).thenReturn(documentation);
        assertFalse(victim.processInterfaceMethod(methodDoc).isPresent());

    }

    @Test
    public void testExtractDocumentationWithoutSuffix() {

        String documentation = "@doclib\n" +
                "methodName: startMethod\n" +
                "methodDescription: Description\n" +
                "return: String - Description\n" +
                "param-1: Int - Description\n" +
                "param-2: XptoObject - Description\n" +
                "";

        when(methodDoc.getRawCommentText()).thenReturn(documentation);
        assertFalse(victim.processInterfaceMethod(methodDoc).isPresent());
    }

    @Test
    public void testExtractValidDocumentation() {

        String documentation = "@doclib\n" +
                "methodName: startMethod\n" +
                "methodDescription: Description\n" +
                "return: String - Description\n" +
                "param-1: Int - Description\n" +
                "param-2: XptoObject - Description\n" +
                "@enddoclib";

        when(methodDoc.getRawCommentText()).thenReturn(documentation);
        Optional<DocMethod> docMethod = victim.processInterfaceMethod(methodDoc);

        assertTrue(docMethod.isPresent());
        assertNull(docMethod.get().getClassName());
        assertEquals("startMethod", docMethod.get().getMethodName());
        assertEquals("Description", docMethod.get().getMethodDescription());
        assertEquals("String", docMethod.get().getReturnObject());
        assertEquals("Int", docMethod.get().getParamObjects().get(1));
        assertEquals("XptoObject", docMethod.get().getParamObjects().get(2));

    }
}
