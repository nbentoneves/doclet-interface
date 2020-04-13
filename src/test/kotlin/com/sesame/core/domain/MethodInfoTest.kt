package com.sesame.core.domain

import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.StringContains.containsString
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Test

class MethodInfoTest {

    @Test
    fun `should verify ToString method`() {

        val methodInfo = MethodInfo.Builder()
                .withBeanIdentification("id.beans")
                .withPackageName("com.test.package")
                .withClassName("Class")
                .withMethodName("MethodName")
                .withMethodDescription("This is a description")
                .withReturnObject("ReturnType")
                .withReturnObjectDescription("Return description")
                .addParamObjects(1, ParameterType.INT)
                .addParamObjects(2, ParameterType.OBJECT)
                .build()

        assertThat(methodInfo.toString(), allOf(
                containsString("beanIdentification=" + methodInfo.beanIdentification),
                containsString("packageName=" + methodInfo.packageName),
                containsString("className=" + methodInfo.className),
                containsString("methodName=" + methodInfo.methodName),
                containsString("methodDescription=" + methodInfo.methodDescription),
                containsString("returnObject=" + methodInfo.returnObject),
                containsString("returnObjectDescription=" + methodInfo.returnObjectDescription),
                containsString("paramObjects=" + methodInfo.paramObjects)))

    }

    @Test
    fun `should verify get method`() {

        val methodInfo = MethodInfo.Builder()
                .withBeanIdentification("id.beans")
                .withPackageName("com.test.package")
                .withClassName("Class")
                .withMethodName("MethodName")
                .withMethodDescription("This is a description")
                .withReturnObject("ReturnType")
                .withReturnObjectDescription("Return description")
                .addParamObjects(1, ParameterType.INT)
                .addParamObjects(2, ParameterType.BOOLEAN)
                .build()


        assertEquals("com.test.package", methodInfo.packageName)
        assertEquals("Class", methodInfo.className)
        assertEquals("MethodName", methodInfo.methodName)
        assertEquals("This is a description", methodInfo.methodDescription)
        assertEquals("ReturnType", methodInfo.returnObject)
        assertEquals("Return description", methodInfo.returnObjectDescription)
        assertEquals("id.beans", methodInfo.beanIdentification)
        assertEquals(ParameterType.INT, methodInfo.paramObjects[1])
        assertEquals(ParameterType.BOOLEAN, methodInfo.paramObjects[2])

    }

}