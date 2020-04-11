package com.sesame.core.json

import com.sesame.domain.internal.ParameterType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class JsonDeserializableTest {

    private lateinit var jsonDeserializable: JsonDeserializable

    @Before
    fun before() {
        jsonDeserializable = JsonDeserializable()
    }

    @Test(expected = IllegalStateException::class)
    fun `verify deserialize when jsonData is empty`() {
        jsonDeserializable.deserialize("", mapOf(Pair(1, ParameterType.INT)))
    }

    @Test
    fun `verify deserialize when jsonData has invalid format`() {
        assertTrue(jsonDeserializable.deserialize(getContentFile("invalidJsonFormat"),
                mapOf(Pair(1, ParameterType.INT))).isEmpty())
    }

    @Test(expected = IllegalStateException::class)
    fun `verify deserialize when parametersToMap is empty`() {
        jsonDeserializable.deserialize(getContentFile("validJsonFormatNumber"), mapOf())
    }

    @Test
    fun `verify deserialize when not find a correct key to mapping`() {
        assertTrue(jsonDeserializable.deserialize(getContentFile("validJsonFormatDouble"),
                mapOf(Pair(20, ParameterType.INT), Pair(2, ParameterType.INT))).isEmpty())
    }

    @Test(expected = UnsupportedOperationException::class)
    fun `verify deserialize when isPrimitive is enable`() {
        jsonDeserializable.deserialize(getContentFile("jsonWithoutIsPrimitiveValue"),
                mapOf(Pair(1, ParameterType.INT), Pair(2, ParameterType.INT)))
    }

    @Test(expected = UnsupportedOperationException::class)
    fun `verify deserialize when isPrimitive is defined with false value`() {
        jsonDeserializable.deserialize(getContentFile("jsonWithIsPrimitiveValueFalse"), mapOf(Pair(1, ParameterType.INT), Pair(2, ParameterType.INT)))
    }

    @Test
    fun `verify deserialize when the number of parameters is not the same of output`() {
        assertTrue(
                jsonDeserializable.deserialize(getContentFile("validJsonFormatInt"), mapOf(
                        Pair(1, ParameterType.INT),
                        Pair(2, ParameterType.INT),
                        Pair(3, ParameterType.BOOLEAN)))
                        .isEmpty()
        )
    }

    @Test
    fun `verify successful deserialize for int`() {
        val param = jsonDeserializable.deserialize(getContentFile("validJsonFormatInt"),
                mapOf(Pair(1, ParameterType.INT), Pair(2, ParameterType.INT)))

        assertEquals(Int.MIN_VALUE, param[1]?.first)
        assertTrue(param[1]?.second == Int::class)
        assertEquals(Int.MAX_VALUE, param[2]?.first)
        assertTrue(param[2]?.second == Int::class)

    }

    @Test
    fun `verify successful deserialize for float`() {
        val param = jsonDeserializable.deserialize(getContentFile("validJsonFormatFloat"),
                mapOf(Pair(1, ParameterType.FLOAT), Pair(2, ParameterType.FLOAT)))

        assertEquals(Float.MIN_VALUE, param[1]?.first)
        assertTrue(param[1]?.second == Float::class)
        assertEquals(Float.MAX_VALUE, param[2]?.first)
        assertTrue(param[2]?.second == Float::class)

    }

    @Test
    fun `verify successful deserialize for long`() {
        val param = jsonDeserializable.deserialize(getContentFile("validJsonFormatLong"),
                mapOf(Pair(1, ParameterType.LONG), Pair(2, ParameterType.LONG)))

        assertEquals(Long.MIN_VALUE, param[1]?.first)
        assertTrue(param[1]?.second == Long::class)
        assertEquals(Long.MAX_VALUE, param[2]?.first)
        assertTrue(param[2]?.second == Long::class)

    }

    @Test
    fun `verify successful deserialize for short`() {
        val param = jsonDeserializable.deserialize(getContentFile("validJsonFormatShort"),
                mapOf(Pair(1, ParameterType.SHORT), Pair(2, ParameterType.SHORT)))

        assertEquals(Short.MIN_VALUE, param[1]?.first)
        assertTrue(param[1]?.second == Short::class)
        assertEquals(Short.MAX_VALUE, param[2]?.first)
        assertTrue(param[2]?.second == Short::class)

    }

    @Test
    fun `verify successful deserialize for byte`() {
        val param = jsonDeserializable.deserialize(getContentFile("validJsonFormatByte"),
                mapOf(Pair(1, ParameterType.BYTE), Pair(2, ParameterType.BYTE)))

        assertEquals(Byte.MIN_VALUE, param[1]?.first)
        assertTrue(param[1]?.second == Byte::class)
        assertEquals(Byte.MAX_VALUE, param[2]?.first)
        assertTrue(param[2]?.second == Byte::class)

    }

    @Test
    fun `verify successful deserialize for char`() {
        val param = jsonDeserializable.deserialize(getContentFile("validJsonFormatChar"),
                mapOf(Pair(1, ParameterType.CHAR), Pair(2, ParameterType.CHAR)))

        assertEquals('A', param[1]?.first)
        assertTrue(param[1]?.second == Char::class)
        assertEquals('Z', param[2]?.first)
        assertTrue(param[2]?.second == Char::class)

    }

    @Test
    fun `verify successful deserialize for boolean`() {
        val param = jsonDeserializable.deserialize(getContentFile("validJsonFormatBoolean"),
                mapOf(Pair(1, ParameterType.BOOLEAN), Pair(2, ParameterType.BOOLEAN)))

        assertEquals(true, param[1]?.first)
        assertTrue(param[1]?.second == Boolean::class)
        assertEquals(false, param[2]?.first)
        assertTrue(param[2]?.second == Boolean::class)

    }

    @Test
    fun `verify successful deserialize for double`() {
        val param = jsonDeserializable.deserialize(getContentFile("validJsonFormatDouble"),
                mapOf(Pair(1, ParameterType.DOUBLE), Pair(2, ParameterType.DOUBLE)))

        assertEquals(Double.MIN_VALUE, param[1]?.first)
        assertTrue(param[1]?.second == Double::class)
        assertEquals(Double.MAX_VALUE, param[2]?.first)
        assertTrue(param[2]?.second == Double::class)

    }

    @Test
    fun `verify successful deserialize for strings`() {
        val param = jsonDeserializable.deserialize(getContentFile("validJsonFormatString"),
                mapOf(Pair(1, ParameterType.STRING), Pair(2, ParameterType.STRING)))

        assertEquals("hello", param[1]?.first)
        assertTrue(param[1]?.second == String::class)
        assertEquals("4564", param[2]?.first)
        assertTrue(param[2]?.second == String::class)

    }

    private fun getContentFile(fileName: String): String {
        val pathFile = javaClass.classLoader.getResource("json/${fileName}.json")

        checkNotNull(pathFile)

        return pathFile.readText(Charsets.UTF_8)
    }

}