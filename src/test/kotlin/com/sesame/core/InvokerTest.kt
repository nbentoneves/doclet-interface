package com.sesame.core

import com.sesame.core.json.JsonDeserializable
import com.sesame.core.json.JsonSerializable
import com.sesame.core.test.TestClass
import com.sesame.domain.internal.DocMethod
import com.sesame.domain.internal.ParameterType
import io.mockk.MockKAnnotations.init
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.context.ApplicationContext
import kotlin.reflect.KClass
import kotlin.test.*

//TODO: Create tests for other types
@ExtendWith(MockKExtension::class)
class InvokerTest {

    @MockK
    private lateinit var metadata: DocMethod

    @MockK
    private lateinit var jsonDeserializable: JsonDeserializable

    @MockK
    private lateinit var jsonSerializable: JsonSerializable

    @MockK
    private lateinit var applicationContext: ApplicationContext

    @BeforeTest
    fun before() {
        init(this)
    }

    @Test
    fun `verify invoker when deserializable returns empty`() {
        every { jsonDeserializable.deserialize(any(), any()) } returns mapOf()
        every { metadata.paramObjects } returns mapOf()

        val result = Invoker(metadata, jsonDeserializable, jsonSerializable, applicationContext).method("JSON_DATA")

        assertNotNull(result)
        assertFalse { result.isPresent }
    }

    @Test
    fun `verify invoker when return success response from class with method int parameters values`() {

        val parametersMapping: MutableMap<Int, Pair<Any, KClass<Any>>> = mutableMapOf()

        parametersMapping[1] = Pair(20, ParameterType.INT.internalType.kotlin)
        parametersMapping[2] = Pair(10, ParameterType.INT.internalType.kotlin)

        every { metadata.packageName } returns TestClass::class.java.`package`.name
        every { metadata.className } returns TestClass::class.java.simpleName
        every { metadata.methodName } returns "method"
        every { metadata.paramObjects } returns mapOf(Pair(1, ParameterType.INT), Pair(2, ParameterType.INT))
        every { metadata.beanIdentification } returns "bean.identifier"

        every { jsonDeserializable.deserialize(any(), any()) } returns parametersMapping
        every { jsonSerializable.serialize(any(), any(), arrayOf(20, 10)) } returns "30"

        every { applicationContext.getBean(eq("bean.identifier")) } returns TestClass()

        val result = Invoker(metadata, jsonDeserializable, jsonSerializable, applicationContext).method("JSON_DATA")

        assertNotNull(result)
        assertTrue { result.isPresent }
        assertEquals("30", result.get())

    }

    @Test
    fun `verify invoker when return success response from class with method Integer parameters values`() {

        val parametersMapping: MutableMap<Int, Pair<Any, KClass<Any>>> = mutableMapOf()

        parametersMapping[1] = Pair(20, ParameterType.INTEGER.internalType.kotlin)
        parametersMapping[2] = Pair(10, ParameterType.INTEGER.internalType.kotlin)

        every { metadata.packageName } returns TestClass::class.java.`package`.name
        every { metadata.className } returns TestClass::class.java.simpleName
        every { metadata.methodName } returns "method"
        every { metadata.paramObjects } returns mapOf(Pair(1, ParameterType.INTEGER), Pair(2, ParameterType.INTEGER))
        every { metadata.beanIdentification } returns "bean.identifier"

        every { jsonDeserializable.deserialize(any(), any()) } returns parametersMapping
        every { jsonSerializable.serialize(any(), any(), arrayOf(20, 10)) } returns "30"

        every { applicationContext.getBean(eq("bean.identifier")) } returns TestClass()

        val result = Invoker(metadata, jsonDeserializable, jsonSerializable, applicationContext).method("JSON_DATA")

        assertNotNull(result)
        assertTrue { result.isPresent }
        assertEquals("30", result.get())

    }

    @Test
    fun `verify invoker when return success response from class with method String parameters values`() {

        val parametersMapping: MutableMap<Int, Pair<Any, KClass<Any>>> = mutableMapOf()

        parametersMapping[1] = Pair("Ola", ParameterType.STRING.internalType.kotlin)

        every { metadata.packageName } returns TestClass::class.java.`package`.name
        every { metadata.className } returns TestClass::class.java.simpleName
        every { metadata.methodName } returns "method"
        every { metadata.paramObjects } returns mapOf(Pair(1, ParameterType.STRING))
        every { metadata.beanIdentification } returns "bean.identifier"

        every { jsonDeserializable.deserialize(any(), any()) } returns parametersMapping
        every { jsonSerializable.serialize(any(), any(), arrayOf("Ola")) } returns "true"

        every { applicationContext.getBean(eq("bean.identifier")) } returns TestClass()

        val result = Invoker(metadata, jsonDeserializable, jsonSerializable, applicationContext).method("JSON_DATA")

        assertNotNull(result)
        assertTrue { result.isPresent }
        assertEquals("true", result.get())

    }
}