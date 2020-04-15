package com.sesame.core

import io.mockk.MockKAnnotations.init
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.springframework.context.ApplicationContext

class SesameCoreConfigurationTest {

    private val sesameCoreConfig = SesameCoreConfiguration()

    @MockK
    private lateinit var applicationContext: ApplicationContext

    @Before
    fun before() {
        init(this)
    }

    @Test
    fun `should verify getJsonSerializable`() {
        assertNotNull(sesameCoreConfig.getJsonSerializable())
    }

    @Test
    fun `should verify getJsonDeserializable`() {
        assertNotNull(sesameCoreConfig.getJsonDeserializable())
    }

    @Test
    fun `should verify getSesameBeanPostProcessorConfiguration`(){
        assertNotNull(sesameCoreConfig.getSesameBeanPostProcessorConfiguration(applicationContext))
    }


}