package com.sesame.core.json

import java.lang.reflect.Method
import kotlin.test.*

class JsonSerializableTest {

    private lateinit var method: Method

    private lateinit var instance: Any

    private lateinit var jsonSerializable: JsonSerializable

    @BeforeTest
    fun before() {
        jsonSerializable = JsonSerializable()
    }

    @Test
    fun `verify serializable when list of parameters is emtpy`() {
        val json = jsonSerializable.serialize(instance, method, arrayOf(20, 10))

        assertNotNull(json)
        assertTrue { json.isNotBlank() }
    }

    @Test
    fun `verify serializable when list of parameters not match`() {
        val json = jsonSerializable.serialize(instance, method, arrayOf(20, 10))

        assertNotNull(json)
        assertTrue { json.isNotBlank() }
    }

    @Test
    fun `verify serializable when occuress an exception`() {
        assertFailsWith(Exception::class) {
            jsonSerializable.serialize(instance, method, arrayOf(20, 10))
        }
    }

    @Test
    fun `verify successful serializable`() {
        val instance = Class.forName("com.sesame.testing.TestServiceClass").newInstance()
        val method = instance!!.javaClass.getMethod("calculator", Integer::class.java, Integer::class.java)

        println(jsonSerializable.serialize(instance, method, arrayOf(20, 10)))
    }

}