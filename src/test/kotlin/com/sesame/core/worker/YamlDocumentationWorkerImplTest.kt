package com.sesame.core.worker

import java.io.File
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertFalse

class YamlDocumentationWorkerImplTest {

    lateinit var yamlDocumentationWorkerImpl: YamlDocumentationWorkerImpl

    @BeforeTest
    fun before() {

        yamlDocumentationWorkerImpl = YamlDocumentationWorkerImpl()

    }

    @Test
    fun `verify process interface method when receive empty file`() {

        assertFalse { yamlDocumentationWorkerImpl.processInterfaceMethod(File("")).isPresent }

    }

}