package com.sesame.worker;

import com.sesame.domain.internal.DocMethod;
import com.sesame.domain.internal.ParameterType;
import com.sesame.worker.exception.DocumentInvalidFormatException;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(JUnitParamsRunner.class)
public class TextDocumentationWorkerImplTest {

    private DocumentationWorker<String> victim;

    @Before
    public void setUp() {
        initMocks(this);
        victim = new TextDocumentationWorkerImpl();
    }

    @Test
    public void testExtractEmptyRawCommentValidDocMethod() {
        assertFalse(victim.processInterfaceMethod("").isPresent());
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

        assertFalse(victim.processInterfaceMethod(documentation).isPresent());

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

        assertFalse(victim.processInterfaceMethod(documentation).isPresent());
    }

    @Test
    public void testExtractValidDocumentation() {

        String documentation = "@doclib\n" +
                "className: className\n" +
                "packageName: packageName\n" +
                "methodName: startMethod\n" +
                "methodDescription: Description\n" +
                "return: String - Description\n" +
                "param-1: Int - Description\n" +
                "param-2: XptoSample - Description\n" +
                "@enddoclib";

        Optional<DocMethod> docMethod = victim.processInterfaceMethod(documentation);

        assertTrue(docMethod.isPresent());
        assertEquals("className", docMethod.get().getClassName());
        assertEquals("packageName", docMethod.get().getPackageName());
        assertEquals("startMethod", docMethod.get().getMethodName());
        assertEquals("Description", docMethod.get().getMethodDescription());
        assertEquals("String", docMethod.get().getReturnObject());

        assertEquals(ParameterType.INT, docMethod.get().getParamObjects().get(1));
        assertEquals(ParameterType.OBJECT, docMethod.get().getParamObjects().get(2));

    }

    @Parameters(method = "dataInvalidDocumentation")
    @Test(expected = DocumentInvalidFormatException.class)
    public void testExtractInvalidDocumentation(String methodName, String methodDescription, String returnInfo, String param1, String param2) {

        String documentation = "@doclib\n" +
                methodName + "\n" +
                methodDescription + "\n" +
                returnInfo + "\n" +
                param1 + "\n" +
                param2 + "\n" +
                "@enddoclib";

        Optional<DocMethod> docMethod = victim.processInterfaceMethod(documentation);

        assertTrue(docMethod.isPresent());
        assertEquals("startMethod", docMethod.get().getMethodName());
        assertEquals("Description", docMethod.get().getMethodDescription());
        assertEquals("String", docMethod.get().getReturnObject());
        assertEquals(ParameterType.INT, docMethod.get().getParamObjects().get(1));
        assertEquals(ParameterType.OBJECT, docMethod.get().getParamObjects().get(2));

    }

    @SuppressWarnings("unused")
    private static Object[][] dataInvalidDocumentation() {
        return new Object[][]{
                //Invalid methodName format (- instead of :) E.g (methodName - startMethod)
                {"methodName - startMethod", "methodDescription: Description", "return: String - Description", "param-1: Int - Description", "param-2: XptoObject - Description"},
                //Invalid methodDescription format (- instead of :) E.g (methodDescription - Description)
                {"methodName: startMethod", "methodDescription - Description", "return: String - Description", "param-1: Int - Description", "param-2: XptoObject - Description"},
                //Invalid return format (- instead of :) E.g (return - String - Description)
                {"methodName: startMethod", "methodDescription: Description", "return - String - Description", "param-1: Int - Description", "param-2: XptoObject - Description"},
                //Invalid return description format (: instead of -) E.g (return: String : Description)
                {"methodName: startMethod", "methodDescription: Description", "return: String : Description", "param-1: Int - Description", "param-2: XptoObject - Description"},
                //Invalid param1 format (_ instead of -) E.g (param_1: Int - Description)
                {"methodName: startMethod", "methodDescription: Description", "return: String - Description", "param_1: Int - Description", "param-2: XptoObject - Description"},
                //Invalid param1 format (- instead of :) E.g (param-1- Int - Description)
                {"methodName: startMethod", "methodDescription: Description", "return: String - Description", "param-1- Int - Description", "param-2: XptoObject - Description"},
                //Invalid param1 description format (_ instead of -)  E.g (param-1: Int _ Description)
                {"methodName: startMethod", "methodDescription: Description", "return: String - Description", "param-1: Int _ Description", "param-2: XptoObject - Description"},
        };
    }
}
