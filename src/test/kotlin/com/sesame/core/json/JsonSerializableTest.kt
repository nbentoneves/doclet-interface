package com.sesame.core.json

import io.mockk.MockKAnnotations.init
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class JsonSerializableTest {

    private lateinit var jsonSerializable: JsonSerializable

    @Before
    fun before() {
        init(this)
        jsonSerializable = JsonSerializable()
    }

    @Test
    fun `verify serializable when list of parameters is empty`() {

        val instance = Class.forName("com.sesame.core.test.TestClass").newInstance()
        val method = instance!!.javaClass.getMethod("method", Int::class.java)

        val json = jsonSerializable.serialize(instance, method, arrayOf())

        assertNotNull(json)
        assertTrue(json.isNotBlank())
    }

    @Test
    fun `verify successful serializable`() {
        val instance = Class.forName("com.sesame.core.test.TestClass").newInstance()
        val method = instance!!.javaClass.getMethod("method", Int::class.java)

        val json = jsonSerializable.serialize(instance, method, arrayOf(20))
        assertNotNull(json)
        assertEquals("20", json)
    }

}