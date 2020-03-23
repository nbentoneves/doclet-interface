package com.sesame.core.json

import com.sesame.domain.internal.ParameterType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import javax.naming.OperationNotSupportedException


class JsonDeserializableTest {

    private lateinit var jsonDeserializable: JsonDeserializable

    @Before
    fun before() {
        jsonDeserializable = JsonDeserializable()
    }

    @Test(expected = DeserializationException::class)
    fun `verify deserialize when jsonData is empty`() {
        jsonDeserializable.deserialize("", mapOf(Pair(1, ParameterType.INT)))
    }

    @Test(expected = DeserializationException::class)
    fun `verify deserialize when jsonData has invalid format`() {
        jsonDeserializable.deserialize(getContentFile("invalidJsonFormat"), mapOf(Pair(1, ParameterType.INT)))
    }

    @Test(expected = IllegalStateException::class)
    fun `verify deserialize when parametersToMap is empty`() {
        jsonDeserializable.deserialize(getContentFile("validJsonFormat"), mapOf())
    }

    @Test(expected = DeserializationException::class)
    fun `verify deserialize when not find a correct key to mapping`() {
        jsonDeserializable.deserialize(getContentFile("validJsonFormat"),
                mapOf(Pair(20, ParameterType.INT), Pair(2, ParameterType.INT)))
    }

    @Test(expected = DeserializationException::class)
    fun `verify deserialize when isPrimitive not defined value`() {
        jsonDeserializable.deserialize(getContentFile("jsonWithoutIsPrimitiveValue"),
                mapOf(Pair(1, ParameterType.INT), Pair(2, ParameterType.INT)))
    }

    @Test(expected = OperationNotSupportedException::class)
    fun `verify deserialize when isPrimitive is defined with false value`() {
        jsonDeserializable.deserialize(getContentFile("jsonWithIsPrimitiveValueFalse"), mapOf(Pair(1, ParameterType.INT), Pair(2, ParameterType.INT)))
    }

    @Test
    fun `verify deserialize when the number of parameters is not the same of output`() {
        assertTrue(
                jsonDeserializable.deserialize(getContentFile("validJsonFormat"),
                        mapOf(Pair(1, ParameterType.INT), Pair(2, ParameterType.INT), Pair(3, ParameterType.BOOLEAN))).isEmpty()
        )
    }

    @Test
    fun `verify successful deserialize`() {
        val param = jsonDeserializable.deserialize(getContentFile("validJsonFormat"),
                mapOf(Pair(1, ParameterType.INT), Pair(2, ParameterType.INT)))

        assertEquals(2, param[1]?.first)
        assertTrue(param[1]?.second == Int::class)
        assertEquals(5, param[2]?.first)
        assertTrue(param[2]?.second == Int::class)

    }

    private fun getContentFile(fileName: String): String {
        val pathFile = javaClass.classLoader.getResource("json/${fileName}.json")

        checkNotNull(pathFile)

        return pathFile.readText(Charsets.UTF_8)
    }

}