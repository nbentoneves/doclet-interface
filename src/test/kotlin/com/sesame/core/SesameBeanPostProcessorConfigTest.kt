package com.sesame.core

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.springframework.beans.factory.support.BeanDefinitionRegistry
import org.springframework.context.ApplicationContext

//FIXME: Find a way to tests this post processor
class SesameBeanPostProcessorConfigTest {

    private lateinit var sesameBeanPostProcessorConfiguration: SesameBeanPostProcessorConfiguration

    @MockK
    private lateinit var applicationContext: ApplicationContext

    @MockK
    private lateinit var registry: BeanDefinitionRegistry

    @Before
    fun before() {
        MockKAnnotations.init(this)
        sesameBeanPostProcessorConfiguration = SesameBeanPostProcessorConfiguration(applicationContext)
    }

    @Ignore
    @Test
    fun `should verify load properties using post processor`() {

        every { applicationContext.environment.getProperty(eq("config.file.path.application.context")) } returns "config/path"
        sesameBeanPostProcessorConfiguration.postProcessBeanDefinitionRegistry(registry)

    }


}