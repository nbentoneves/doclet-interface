package com.sesame.core.worker

import com.sesame.domain.internal.DocMethod
import java.io.File
import java.util.*

class YamlDocumentationWorkerImpl : DocumentationWorker {

    override fun processInterfaceMethod(file: File): Optional<DocMethod> {
        return Optional.empty()
    }
}