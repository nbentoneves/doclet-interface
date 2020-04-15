package com.sesame.core.worker

import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DocumentationFactoryImplTest {

    @MockK
    private lateinit var textDocumentationWorkerImpl: TextDocumentationWorkerImpl

    @MockK
    private lateinit var yamlDocumentationWorkerImpl: YamlDocumentationWorkerImpl

    private lateinit var documentationFactoryImpl: DocumentationFactoryImpl

    @Before
    fun before() {
        MockKAnnotations.init(this)

        documentationFactoryImpl = DocumentationFactoryImpl(textDocumentationWorkerImpl,
                yamlDocumentationWorkerImpl)
    }

    @Test
    fun `should verify getTextDocumentationWorker`() {
        assertNotNull(documentationFactoryImpl.getTextDocumentationWorker())
    }

    @Test
    fun `should verify getYamlDocumentationWorker`() {
        assertNotNull(documentationFactoryImpl.getYamlDocumentationWorker())
    }

}