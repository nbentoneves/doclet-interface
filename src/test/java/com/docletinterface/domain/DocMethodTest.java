package com.docletinterface.domain;

import org.junit.Test;

import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class DocMethodTest {

    @Test
    public void testToString() {

        DocMethod docMethod = new DocMethod.Builder()
                .withClassName("Class")
                .withMethodName("MethodName")
                .withMethodDescription("This is a description")
                .withReturnObject("ReturnType")
                .addParamObjects(1, "Int")
                .addParamObjects(2, "String")
                .build();

        assertThat(docMethod.toString(), allOf(
                containsString("className='" + docMethod.getClassName() + "'"),
                containsString("methodName='" + docMethod.getMethodName() + "'"),
                containsString("methodDescription='" + docMethod.getMethodDescription() + "'"),
                containsString("returnObject='" + docMethod.getReturnObject() + "'"),
                containsString("paramObjects=" + docMethod.getParamObjects() + "")));

    }

    @Test
    public void testGetDocMethod() {

        DocMethod docMethod = new DocMethod.Builder()
                .withClassName("Class")
                .withMethodName("MethodName")
                .withMethodDescription("This is a description")
                .withReturnObject("ReturnType")
                .addParamObjects(1, "Int")
                .addParamObjects(2, "String")
                .build();

        assertEquals("Class", docMethod.getClassName());
        assertEquals("MethodName", docMethod.getMethodName());
        assertEquals("This is a description", docMethod.getMethodDescription());
        assertEquals("ReturnType", docMethod.getReturnObject());
        assertEquals("Int", docMethod.getParamObjects().get(1));
        assertEquals("String", docMethod.getParamObjects().get(2));

    }

}
