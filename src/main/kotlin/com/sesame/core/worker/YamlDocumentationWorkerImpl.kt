package com.sesame.core.worker

import com.sesame.core.domain.MethodInfo
import org.springframework.stereotype.Component
import java.io.File
import java.util.*

@Component
class YamlDocumentationWorkerImpl : DocumentationWorker {

    override fun processInterfaceMethod(file: File): Optional<MethodInfo> {
        return Optional.empty()
    }
}