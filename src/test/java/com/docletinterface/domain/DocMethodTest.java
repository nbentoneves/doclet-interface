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
                .withPackageName("com.test.package")
                .withClassName("Class")
                .withMethodName("MethodName")
                .withMethodDescription("This is a description")
                .withReturnObject("ReturnType")
                .withReturnObjectDescription("Return description")
                .addParamObjects(1, ParameterType.INT)
                .addParamObjects(2, ParameterType.OBJECT)
                .build();

        assertThat(docMethod.toString(), allOf(
                containsString("packageName='" +docMethod.getPackageName()+ "'"),
                containsString("className='" + docMethod.getClassName() + "'"),
                containsString("methodName='" + docMethod.getMethodName() + "'"),
                containsString("methodDescription='" + docMethod.getMethodDescription() + "'"),
                containsString("returnObject='" + docMethod.getReturnObject() + "'"),
                containsString("returnObjectDescription='" + docMethod.getReturnObjectDescription() + "'"),
                containsString("paramObjects=" + docMethod.getParamObjects() + "")));

    }

    @Test
    public void testGetDocMethod() {

        DocMethod docMethod = new DocMethod.Builder()
                .withPackageName("com.test.package")
                .withClassName("Class")
                .withMethodName("MethodName")
                .withMethodDescription("This is a description")
                .withReturnObject("ReturnType")
                .addParamObjects(1, ParameterType.INT)
                .addParamObjects(2, ParameterType.BOOLEAN)
                .build();

        assertEquals("Class", docMethod.getClassName());
        assertEquals("MethodName", docMethod.getMethodName());
        assertEquals("This is a description", docMethod.getMethodDescription());
        assertEquals("ReturnType", docMethod.getReturnObject());
        assertEquals(ParameterType.INT, docMethod.getParamObjects().get(1));
        assertEquals(ParameterType.BOOLEAN, docMethod.getParamObjects().get(2));

    }

}
