package com.sesame.core.worker

import org.yaml.snakeyaml.Yaml
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
    fun `verify process interface method when receive null or empty value`() {

        assertFalse { yamlDocumentationWorkerImpl.processInterfaceMethod(null).isPresent }
        assertFalse { yamlDocumentationWorkerImpl.processInterfaceMethod(Yaml()).isPresent }

    }

}