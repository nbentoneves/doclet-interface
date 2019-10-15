package com.docletinterface.doclet;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.RootDoc;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static com.docletinterface.doclet.GenDocletInterface.getDocMethod;
import static com.docletinterface.doclet.GenDocletInterface.start;
import static org.junit.Assert.*;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GenDocletInterfaceTest {

    @Mock
    public ClassDoc classDoc;

    @Mock
    public MethodDoc methodDoc;

    @Mock
    public RootDoc rootDoc;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @After
    public void cleanUp() {
        reset(rootDoc, methodDoc, classDoc);
    }

    @Test
    public void testWhenRootDocIsNull() {
        assertFalse(start(null));
    }

    @Test
    public void testWhenRootDocNoExistClasses() {

        when(rootDoc.specifiedClasses()).thenReturn(null);
        assertFalse(start(rootDoc));

    }

    @Test(expected = RuntimeException.class)
    public void testWhenRootDocIsInvalid() {

        when(rootDoc.specifiedClasses()[0].methods()[0]).thenReturn(null);
        start(rootDoc);

    }

    @Test
    public void testWhenRootDocIsValid() {

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

        when(classDoc.methods()).thenReturn(new MethodDoc[]{methodDoc});
        when(methodDoc.getRawCommentText()).thenReturn(rawCommentTest);
        when(rootDoc.specifiedClasses()).thenReturn(new ClassDoc[]{classDoc});

        assertTrue(start(rootDoc));
        assertNotNull(getDocMethod());

    }

}
