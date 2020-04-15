package com.sesame.core

import com.sesame.core.domain.MethodInfo
import com.sesame.core.domain.ParameterType
import com.sesame.core.json.DeserializationException
import com.sesame.core.json.JsonDeserializable
import com.sesame.core.json.JsonSerializable
import com.sesame.core.test.TestClass
import io.mockk.MockKAnnotations.init
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.springframework.context.ApplicationContext

class InvokerTest {

    @MockK
    private lateinit var metadata: MethodInfo

    @MockK
    private lateinit var jsonDeserializable: JsonDeserializable

    @MockK
    private lateinit var jsonSerializable: JsonSerializable

    @MockK
    private lateinit var applicationContext: ApplicationContext

    @Before
    fun before() {
        init(this)
    }

    @Test
    fun `verify invoker when deserializable returns empty`() {
        every { jsonDeserializable.deserialize(any(), any()) } returns mapOf()
        every { metadata.paramObjects } returns mapOf()

        val result = Invoker(metadata, jsonDeserializable, jsonSerializable, applicationContext).method("JSON_DATA")

        assertNotNull(result)
        assertFalse(result.isPresent)
    }

    @Test(expected = Exception::class)
    fun `verify invoker when something throws an exception`() {
        every { jsonDeserializable.deserialize(any(), any()) } throws DeserializationException("")
        every { metadata.paramObjects } returns mapOf()

        Invoker(metadata, jsonDeserializable, jsonSerializable, applicationContext).method("JSON_DATA")
    }

    @Test
    fun `verify invoker when return success response from class with method boolean at parameters using reflection`() {

        val value: Boolean = true
        val parametersMapping = mapOf(Pair(1, Pair(value, ParameterType.BOOLEAN.internalType.kotlin)))
        val expectedParamObjects = mapOf(Pair(1, ParameterType.BOOLEAN))

        every { metadata.packageName } returns TestClass::class.java.`package`.name
        every { metadata.className } returns TestClass::class.java.simpleName
        every { metadata.methodName } returns "method"
        every { metadata.paramObjects } returns expectedParamObjects
        every { metadata.beanIdentification } returns null

        val expectedMethod = TestClass::class.java.getMethod("method", ParameterType.BOOLEAN.internalType)
        val expectedParameters: Array<Any> = arrayOf(value)

        every { jsonDeserializable.deserialize(eq("JSON_DATA"), eq(expectedParamObjects)) } returns parametersMapping
        every { jsonSerializable.serialize(any(), eq(expectedMethod), eq(expectedParameters)) } returns "true"

        val result = Invoker(metadata, jsonDeserializable, jsonSerializable, applicationContext).method("JSON_DATA")

        assertNotNull(result)
        assertTrue(result.isPresent)
        assertEquals("true", result.get())

    }

    @Test
    fun `verify invoker when return success response from class with method boolean at parameters using dependency injection`() {

        val value: Boolean = true
        val parametersMapping = mapOf(Pair(1, Pair(value, ParameterType.BOOLEAN.internalType.kotlin)))
        val expectedParamObjects = mapOf(Pair(1, ParameterType.BOOLEAN))

        every { metadata.packageName } returns TestClass::class.java.`package`.name
        every { metadata.className } returns TestClass::class.java.simpleName
        every { metadata.methodName } returns "method"
        every { metadata.paramObjects } returns expectedParamObjects
        every { metadata.beanIdentification } returns "bean.identifier"

        val expectedMethod = TestClass::class.java.getMethod("method", ParameterType.BOOLEAN.internalType)
        val expectedClass = TestClass()
        val expectedParameters: Array<Any> = arrayOf(value)

        every { applicationContext.getBean(eq("bean.identifier")) } returns expectedClass
        every { jsonDeserializable.deserialize(eq("JSON_DATA"), eq(expectedParamObjects)) } returns parametersMapping
        every { jsonSerializable.serialize(eq(expectedClass), eq(expectedMethod), eq(expectedParameters)) } returns "true"

        val result = Invoker(metadata, jsonDeserializable, jsonSerializable, applicationContext).method("JSON_DATA")

        assertNotNull(result)
        assertTrue(result.isPresent)
        assertEquals("true", result.get())

    }

    @Test
    fun `verify invoker when return success response from class with method char at parameters using dependency injection`() {

        val value = 'a'
        val parametersMapping = mapOf(Pair(1, Pair(value, ParameterType.CHAR.internalType.kotlin)))
        val expectedParamObjects = mapOf(Pair(1, ParameterType.CHAR))

        every { metadata.packageName } returns TestClass::class.java.`package`.name
        every { metadata.className } returns TestClass::class.java.simpleName
        every { metadata.methodName } returns "method"
        every { metadata.paramObjects } returns expectedParamObjects
        every { metadata.beanIdentification } returns "bean.identifier"

        val expectedMethod = TestClass::class.java.getMethod("method", ParameterType.CHAR.internalType)
        val expectedClass = TestClass()
        val expectedParameters: Array<Any> = arrayOf(value)

        every { applicationContext.getBean(eq("bean.identifier")) } returns expectedClass
        every { jsonDeserializable.deserialize(eq("JSON_DATA"), eq(expectedParamObjects)) } returns parametersMapping
        every { jsonSerializable.serialize(eq(expectedClass), eq(expectedMethod), eq(expectedParameters)) } returns "a"

        val result = Invoker(metadata, jsonDeserializable, jsonSerializable, applicationContext).method("JSON_DATA")

        assertNotNull(result)
        assertTrue(result.isPresent)
        assertEquals("a", result.get())

    }

    @Test
    fun `verify invoker when return success response from class with method byte at parameters using dependency injections`() {

        val value: Byte = 1
        val parametersMapping = mapOf(Pair(1, Pair(value, ParameterType.BYTE.internalType.kotlin)))
        val expectedParamObjects = mapOf(Pair(1, ParameterType.BYTE))

        every { metadata.packageName } returns TestClass::class.java.`package`.name
        every { metadata.className } returns TestClass::class.java.simpleName
        every { metadata.methodName } returns "method"
        every { metadata.paramObjects } returns expectedParamObjects
        every { metadata.beanIdentification } returns "bean.identifier"

        val expectedMethod = TestClass::class.java.getMethod("method", ParameterType.BYTE.internalType)
        val expectedClass = TestClass()
        val expectedParameters: Array<Any> = arrayOf(value)

        every { applicationContext.getBean(eq("bean.identifier")) } returns expectedClass
        every { jsonDeserializable.deserialize(eq("JSON_DATA"), eq(expectedParamObjects)) } returns parametersMapping
        every { jsonSerializable.serialize(eq(expectedClass), eq(expectedMethod), eq(expectedParameters)) } returns "1"

        val result = Invoker(metadata, jsonDeserializable, jsonSerializable, applicationContext).method("JSON_DATA")

        assertNotNull(result)
        assertTrue(result.isPresent)
        assertEquals("1", result.get())

    }

    @Test
    fun `verify invoker when return success response from class with method short at parameters using dependency injection`() {

        val value: Short = 10
        val parametersMapping = mapOf(Pair(1, Pair(value, ParameterType.SHORT.internalType.kotlin)))
        val expectedParamObjects = mapOf(Pair(1, ParameterType.SHORT))

        every { metadata.packageName } returns TestClass::class.java.`package`.name
        every { metadata.className } returns TestClass::class.java.simpleName
        every { metadata.methodName } returns "method"
        every { metadata.paramObjects } returns expectedParamObjects
        every { metadata.beanIdentification } returns "bean.identifier"

        val expectedMethod = TestClass::class.java.getMethod("method", ParameterType.SHORT.internalType)
        val expectedClass = TestClass()
        val expectedParameters: Array<Any> = arrayOf(value)

        every { applicationContext.getBean(eq("bean.identifier")) } returns expectedClass
        every { jsonDeserializable.deserialize(eq("JSON_DATA"), eq(expectedParamObjects)) } returns parametersMapping
        every { jsonSerializable.serialize(eq(expectedClass), eq(expectedMethod), eq(expectedParameters)) } returns "10"

        val result = Invoker(metadata, jsonDeserializable, jsonSerializable, applicationContext).method("JSON_DATA")

        assertNotNull(result)
        assertTrue(result.isPresent)
        assertEquals("10", result.get())

    }

    @Test
    fun `verify invoker when return success response from class with method int at parameters using dependency injection`() {

        val value: Int = 10
        val parametersMapping = mapOf(Pair(1, Pair(value, ParameterType.INT.internalType.kotlin)))
        val expectedParamObjects = mapOf(Pair(1, ParameterType.INT))

        every { metadata.packageName } returns TestClass::class.java.`package`.name
        every { metadata.className } returns TestClass::class.java.simpleName
        every { metadata.methodName } returns "method"
        every { metadata.paramObjects } returns expectedParamObjects
        every { metadata.beanIdentification } returns "bean.identifier"

        val expectedMethod = TestClass::class.java.getMethod("method", ParameterType.INT.internalType)
        val expectedClass = TestClass()
        val expectedParameters: Array<Any> = arrayOf(value)

        every { applicationContext.getBean(eq("bean.identifier")) } returns expectedClass
        every { jsonDeserializable.deserialize(eq("JSON_DATA"), eq(expectedParamObjects)) } returns parametersMapping
        every { jsonSerializable.serialize(eq(expectedClass), eq(expectedMethod), eq(expectedParameters)) } returns "10"

        val result = Invoker(metadata, jsonDeserializable, jsonSerializable, applicationContext).method("JSON_DATA")

        assertNotNull(result)
        assertTrue(result.isPresent)
        assertEquals("10", result.get())

    }

    @Test
    fun `verify invoker when return success response from class with method long at parameters using dependency injection`() {

        val value: Long = 1000
        val parametersMapping = mapOf(Pair(1, Pair(value, ParameterType.LONG.internalType.kotlin)))
        val expectedParamObjects = mapOf(Pair(1, ParameterType.LONG))

        every { metadata.packageName } returns TestClass::class.java.`package`.name
        every { metadata.className } returns TestClass::class.java.simpleName
        every { metadata.methodName } returns "method"
        every { metadata.paramObjects } returns expectedParamObjects
        every { metadata.beanIdentification } returns "bean.identifier"

        val expectedMethod = TestClass::class.java.getMethod("method", ParameterType.LONG.internalType)
        val expectedClass = TestClass()
        val expectedParameters: Array<Any> = arrayOf(value)

        every { applicationContext.getBean(eq("bean.identifier")) } returns expectedClass
        every { jsonDeserializable.deserialize(eq("JSON_DATA"), eq(expectedParamObjects)) } returns parametersMapping
        every { jsonSerializable.serialize(eq(expectedClass), eq(expectedMethod), eq(expectedParameters)) } returns "1000"


        val result = Invoker(metadata, jsonDeserializable, jsonSerializable, applicationContext).method("JSON_DATA")

        assertNotNull(result)
        assertTrue(result.isPresent)
        assertEquals("1000", result.get())

    }

    @Test
    fun `verify invoker when return success response from class with method float at parameters using dependency injection`() {

        val value: Float = 20.5F
        val parametersMapping = mapOf(Pair(1, Pair(value, ParameterType.FLOAT.internalType.kotlin)))
        val expectedParamObjects = mapOf(Pair(1, ParameterType.FLOAT))

        every { metadata.packageName } returns TestClass::class.java.`package`.name
        every { metadata.className } returns TestClass::class.java.simpleName
        every { metadata.methodName } returns "method"
        every { metadata.paramObjects } returns expectedParamObjects
        every { metadata.beanIdentification } returns "bean.identifier"

        val expectedMethod = TestClass::class.java.getMethod("method", ParameterType.FLOAT.internalType)
        val expectedClass = TestClass()
        val expectedParameters: Array<Any> = arrayOf(value)

        every { applicationContext.getBean(eq("bean.identifier")) } returns expectedClass
        every { jsonDeserializable.deserialize(eq("JSON_DATA"), eq(expectedParamObjects)) } returns parametersMapping
        every { jsonSerializable.serialize(eq(expectedClass), eq(expectedMethod), eq(expectedParameters)) } returns "20.5"


        val result = Invoker(metadata, jsonDeserializable, jsonSerializable, applicationContext).method("JSON_DATA")

        assertNotNull(result)
        assertTrue(result.isPresent)
        assertEquals("20.5", result.get())

    }

    @Test
    fun `verify invoker when return success response from class with method double at parameters using dependency injection`() {

        val value: Double = 200000000.5
        val parametersMapping = mapOf(Pair(1, Pair(value, ParameterType.FLOAT.internalType.kotlin)))
        val expectedParamObjects = mapOf(Pair(1, ParameterType.FLOAT))

        every { metadata.packageName } returns TestClass::class.java.`package`.name
        every { metadata.className } returns TestClass::class.java.simpleName
        every { metadata.methodName } returns "method"
        every { metadata.paramObjects } returns expectedParamObjects
        every { metadata.beanIdentification } returns "bean.identifier"

        val expectedMethod = TestClass::class.java.getMethod("method", ParameterType.FLOAT.internalType)
        val expectedClass = TestClass()
        val expectedParameters: Array<Any> = arrayOf(value)

        every { applicationContext.getBean(eq("bean.identifier")) } returns expectedClass
        every { jsonDeserializable.deserialize(eq("JSON_DATA"), eq(expectedParamObjects)) } returns parametersMapping
        every { jsonSerializable.serialize(eq(expectedClass), eq(expectedMethod), eq(expectedParameters)) } returns "200000000.5"


        val result = Invoker(metadata, jsonDeserializable, jsonSerializable, applicationContext).method("JSON_DATA")

        assertNotNull(result)
        assertTrue(result.isPresent)
        assertEquals("200000000.5", result.get())

    }

    @Test
    fun `verify invoker when return success response from class with method String at parameters using dependency injection`() {

        val value: String = "Hello World"
        val parametersMapping = mapOf(Pair(1, Pair(value, ParameterType.STRING.internalType.kotlin)))
        val expectedParamObjects = mapOf(Pair(1, ParameterType.STRING))

        every { metadata.packageName } returns TestClass::class.java.`package`.name
        every { metadata.className } returns TestClass::class.java.simpleName
        every { metadata.methodName } returns "method"
        every { metadata.paramObjects } returns expectedParamObjects
        every { metadata.beanIdentification } returns "bean.identifier"

        val expectedMethod = TestClass::class.java.getMethod("method", ParameterType.STRING.internalType)
        val expectedClass = TestClass()
        val expectedParameters: Array<Any> = arrayOf(value)

        every { applicationContext.getBean(eq("bean.identifier")) } returns expectedClass
        every { jsonDeserializable.deserialize(eq("JSON_DATA"), eq(expectedParamObjects)) } returns parametersMapping
        every { jsonSerializable.serialize(eq(expectedClass), eq(expectedMethod), eq(expectedParameters)) } returns "Hello World"


        val result = Invoker(metadata, jsonDeserializable, jsonSerializable, applicationContext).method("JSON_DATA")

        assertNotNull(result)
        assertTrue(result.isPresent)
        assertEquals("Hello World", result.get())

    }

    @Test
    fun `verify invoker when return success response from class with method int at parameters with more than one parameter using dependency injection`() {

        val value1: Int = 10
        val value2: Int = 20
        val parametersMapping = mapOf(
                Pair(1, Pair(value1, ParameterType.INT.internalType.kotlin)),
                Pair(2, Pair(value2, ParameterType.INT.internalType.kotlin)))
        val expectedParamObjects = mapOf(
                Pair(1, ParameterType.INT),
                Pair(2, ParameterType.INT))

        every { metadata.packageName } returns TestClass::class.java.`package`.name
        every { metadata.className } returns TestClass::class.java.simpleName
        every { metadata.methodName } returns "method"
        every { metadata.paramObjects } returns expectedParamObjects
        every { metadata.beanIdentification } returns "bean.identifier"

        val expectedMethod = TestClass::class.java.getMethod("method", *parametersMapping.map { it.value.second.java }.toTypedArray())
        val expectedClass = TestClass()
        val expectedParameters: Array<Any> = arrayOf(value1, value2)

        every { applicationContext.getBean(eq("bean.identifier")) } returns expectedClass
        every { jsonDeserializable.deserialize(eq("JSON_DATA"), eq(expectedParamObjects)) } returns parametersMapping
        every { jsonSerializable.serialize(eq(expectedClass), eq(expectedMethod), eq(expectedParameters)) } returns "30"


        val result = Invoker(metadata, jsonDeserializable, jsonSerializable, applicationContext).method("JSON_DATA")

        assertNotNull(result)
        assertTrue(result.isPresent)
        assertEquals("30", result.get())

    }

}