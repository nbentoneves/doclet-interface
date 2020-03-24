package com.sesame.core.worker

import com.sesame.domain.internal.DocMethod
import com.sesame.worker.DocumentationFactory
import io.mockk.MockKAnnotations.init
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.util.*

class SwitchWorkerTest {

    private lateinit var victim: SwitchWorker

    @MockK
    private lateinit var textDocumentationWorker: DocumentationWorker

    @MockK
    private lateinit var yamlDocumentationWorker: YamlDocumentationWorkerImpl

    @MockK
    private lateinit var documentationFactory: DocumentationFactory

    @Before
    fun setupClass() {
        init(this)
        victim = SwitchWorker(documentationFactory)

        every { documentationFactory.textDocumentationWorker } returns textDocumentationWorker
        every { documentationFactory.yamlDocumentationWorker } returns yamlDocumentationWorker
    }

    @Test
    fun testWhenConfigPathIsNullOrEmpty() {

        assertFalse(victim.start("TEXT", null).isPresent)
        assertFalse(victim.start("TEXT", "").isPresent)
    }

    @Test
    fun testWhenConfigTypeIsNullOrEmpty() {

        assertFalse(victim.start(null, "PATH").isPresent)
        assertFalse(victim.start("", "PATH").isPresent)

    }

    @Test
    fun testWhenConfigTypeNotExists() {

        assertFalse(victim.start("XPTO", javaClass.classLoader.getResource("config/yamlTemplate.yaml")!!.path).isPresent)

    }

    @Test
    fun testWhenTextDocumentationWorkerReturnEmpty() {

        every { textDocumentationWorker.processInterfaceMethod(any()) } returns Optional.empty()
        assertFalse(victim.start("TEXT", javaClass.classLoader.getResource("config/config.txt")!!.path).isPresent)

    }

    @Test
    fun testWhenYamlDocumentationWorkerReturnEmpty() {

        every { yamlDocumentationWorker.processInterfaceMethod(any()) } returns Optional.empty()
        assertFalse(victim.start("YAML", javaClass.classLoader.getResource("config/yamlTemplate.yaml")!!.path).isPresent)

    }

    @Test
    fun testWhenWorkerReturnValidDocMethod() {

        every { yamlDocumentationWorker.processInterfaceMethod(any()) } returns Optional.of(DocMethod.Builder().build())
        assertTrue(victim.start("YAML", javaClass.classLoader.getResource("config/yamlTemplate.yaml")!!.path).isPresent)

    }

}