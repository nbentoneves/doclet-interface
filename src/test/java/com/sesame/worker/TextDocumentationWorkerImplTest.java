package com.sesame.worker;

import com.sesame.core.worker.DocumentationWorker;
import com.sesame.domain.internal.DocMethod;
import com.sesame.domain.internal.ParameterType;
import com.sesame.worker.exception.DocumentInvalidFormatException;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(JUnitParamsRunner.class)
public class TextDocumentationWorkerImplTest {

    private DocumentationWorker victim;

    @Before
    public void setUp() {
        initMocks(this);
        victim = new TextDocumentationWorkerImpl();
    }

    @Test
    public void testExtractNullDocMethod() {

        assertFalse(victim.processInterfaceMethod(null).isPresent());

    }

    @Test
    public void testExtractDocumentationWithoutPrefix() throws Exception {

        String documentation = "\n" +
                "methodName: startMethod\n" +
                "methodDescription: Description\n" +
                "return: String - Description\n" +
                "param-1: Int - Description\n" +
                "param-2: XptoObject - Description\n" +
                "@enddoclib ";

        File file = createTemporaryFile(documentation);

        assertFalse(victim.processInterfaceMethod(file).isPresent());

        file.deleteOnExit();

    }

    @Test
    public void testExtractDocumentationWithoutSuffix() throws Exception {

        String documentation = "@doclib\n" +
                "methodName: startMethod\n" +
                "methodDescription: Description\n" +
                "return: String - Description\n" +
                "param-1: Int - Description\n" +
                "param-2: XptoObject - Description\n" +
                "";

        File file = createTemporaryFile(documentation);

        assertFalse(victim.processInterfaceMethod(file).isPresent());

        file.deleteOnExit();
    }

    @Test
    public void testExtractValidDocumentation() throws Exception {

        String documentation = "@doclib\n" +
                "className: className\n" +
                "packageName: packageName\n" +
                "methodName: startMethod\n" +
                "methodDescription: Description\n" +
                "return: String - Description\n" +
                "param-1: Int - Description\n" +
                "param-2: XptoSample - Description\n" +
                "@enddoclib";

        File file = createTemporaryFile(documentation);

        Optional<DocMethod> docMethod = victim.processInterfaceMethod(file);

        assertTrue(docMethod.isPresent());
        assertEquals("className", docMethod.get().getClassName());
        assertEquals("packageName", docMethod.get().getPackageName());
        assertEquals("startMethod", docMethod.get().getMethodName());
        assertEquals("Description", docMethod.get().getMethodDescription());
        assertEquals("String", docMethod.get().getReturnObject());

        assertEquals(ParameterType.INT, docMethod.get().getParamObjects().get(1));
        assertEquals(ParameterType.OBJECT, docMethod.get().getParamObjects().get(2));

        file.deleteOnExit();

    }

    @Parameters(method = "dataInvalidDocumentation")
    @Test(expected = DocumentInvalidFormatException.class)
    public void testExtractInvalidDocumentation(String methodName, String methodDescription, String returnInfo, String param1, String param2) throws Exception {

        String documentation = "@doclib\n" +
                methodName + "\n" +
                methodDescription + "\n" +
                returnInfo + "\n" +
                param1 + "\n" +
                param2 + "\n" +
                "@enddoclib";

        File file = createTemporaryFile(documentation);

        Optional<DocMethod> docMethod = victim.processInterfaceMethod(file);

        assertTrue(docMethod.isPresent());
        assertEquals("startMethod", docMethod.get().getMethodName());
        assertEquals("Description", docMethod.get().getMethodDescription());
        assertEquals("String", docMethod.get().getReturnObject());
        assertEquals(ParameterType.INT, docMethod.get().getParamObjects().get(1));
        assertEquals(ParameterType.OBJECT, docMethod.get().getParamObjects().get(2));

        file.deleteOnExit();

    }

    private File createTemporaryFile(String fileContent) throws Exception {

        File tmpFile = File.createTempFile("tempFile", ".doc");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tmpFile));

        bufferedWriter.write(fileContent);
        bufferedWriter.close();

        return tmpFile;

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
