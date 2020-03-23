package com.sesame.core.json

import io.mockk.MockKAnnotations.init
import io.mockk.impl.annotations.MockK
import org.junit.Before
import java.lang.reflect.Method

class JsonSerializableTest {

    @MockK
    private lateinit var method: Method

    @MockK
    private lateinit var instance: Any

    private lateinit var jsonSerializable: JsonSerializable

    @Before
    fun before() {
        init(this)
        jsonSerializable = JsonSerializable()
    }

    //TODO: Enable this tests
/*
@Test
    fun `verify serializable when list of parameters is emtpy`() {
        val json = jsonSerializable.serialize(instance, method, arrayOf(20, 10))

        assertNotNull(json)
        assertTrue(json.isNotBlank())
    }

    @Test
    fun `verify serializable when list of parameters not match`() {
        val json = jsonSerializable.serialize(instance, method, arrayOf(20, 10))

        assertNotNull(json)
        assertTrue(json.isNotBlank())
    }

    @Test(expected = Exception::class)
    fun `verify serializable when occuress an exception`() {
        jsonSerializable.serialize(instance, method, arrayOf(20, 10))
    }

    @Test
    fun `verify successful serializable`() {
        val instance = Class.forName("com.sesame.testing.TestServiceClass").newInstance()
        val method = instance!!.javaClass.getMethod("calculator", Integer::class.java, Integer::class.java)

        println(jsonSerializable.serialize(instance, method, arrayOf(20, 10)))
    }
    */

}