package com.sesame.core.worker

import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import java.io.File

class YamlDocumentationWorkerImplTest {

    lateinit var yamlDocumentationWorkerImpl: YamlDocumentationWorkerImpl

    @Before
    fun before() {

        yamlDocumentationWorkerImpl = YamlDocumentationWorkerImpl()

    }

    @Test
    fun `verify process interface method when receive empty file`() {

        assertFalse(yamlDocumentationWorkerImpl.processInterfaceMethod(File("")).isPresent)

    }

}