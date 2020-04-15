package com.sesame.core.worker

interface DocumentationFactory {

    fun getTextDocumentationWorker(): DocumentationWorker

    fun getYamlDocumentationWorker(): DocumentationWorker
    
}