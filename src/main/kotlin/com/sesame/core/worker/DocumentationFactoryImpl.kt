package com.sesame.core.worker

import org.springframework.stereotype.Component

@Component
class DocumentationFactoryImpl(private val textDocumentationWorker: TextDocumentationWorkerImpl,
                               private val yamlDocumentationWorker: YamlDocumentationWorkerImpl) : DocumentationFactory {

    override fun getTextDocumentationWorker(): DocumentationWorker {
        return this.textDocumentationWorker
    }

    override fun getYamlDocumentationWorker(): DocumentationWorker {
        return this.yamlDocumentationWorker
    }

}