package com.sesame.core.domain

import org.junit.Assert.*
import org.junit.Test

class ParameterTypeTest {

    @Test
    fun `should verify boolean enum`() {
        assertEquals("boolean", ParameterType.BOOLEAN.displayType)
        assertEquals(Boolean::class.java, ParameterType.BOOLEAN.internalType)
        assertTrue(ParameterType.BOOLEAN.isPrimitive)
    }

    @Test
    fun `should verify char enum`() {
        assertEquals("char", ParameterType.CHAR.displayType)
        assertEquals(Char::class.java, ParameterType.CHAR.internalType)
        assertTrue(ParameterType.CHAR.isPrimitive)
    }

    @Test
    fun `should verify byte enum`() {
        assertEquals("byte", ParameterType.BYTE.displayType)
        assertEquals(Byte::class.java, ParameterType.BYTE.internalType)
        assertTrue(ParameterType.BYTE.isPrimitive)
    }

    @Test
    fun `should verify short enum`() {
        assertEquals("short", ParameterType.SHORT.displayType)
        assertEquals(Short::class.java, ParameterType.SHORT.internalType)
        assertTrue(ParameterType.SHORT.isPrimitive)
    }

    @Test
    fun `should verify int enum`() {
        assertEquals("int", ParameterType.INT.displayType)
        assertEquals(Int::class.java, ParameterType.INT.internalType)
        assertTrue(ParameterType.INT.isPrimitive)
    }

    @Test
    fun `should verify long enum`() {
        assertEquals("long", ParameterType.LONG.displayType)
        assertEquals(Long::class.java, ParameterType.LONG.internalType)
        assertTrue(ParameterType.LONG.isPrimitive)
    }

    @Test
    fun `should verify float enum`() {
        assertEquals("float", ParameterType.FLOAT.displayType)
        assertEquals(Float::class.java, ParameterType.FLOAT.internalType)
        assertTrue(ParameterType.FLOAT.isPrimitive)
    }

    @Test
    fun `should verify double enum`() {
        assertEquals("double", ParameterType.DOUBLE.displayType)
        assertEquals(Double::class.java, ParameterType.DOUBLE.internalType)
        assertTrue(ParameterType.DOUBLE.isPrimitive)
    }

    @Test
    fun `should verify string enum`() {
        assertEquals("string", ParameterType.STRING.displayType)
        assertEquals(String::class.java, ParameterType.STRING.internalType)
        assertFalse(ParameterType.STRING.isPrimitive)
    }

    @Test
    fun `should verify object enum`() {
        assertEquals("object", ParameterType.OBJECT.displayType)
        assertEquals(Object::class.java, ParameterType.OBJECT.internalType)
        assertFalse(ParameterType.OBJECT.isPrimitive)
    }


}