package com.sesame.core.worker

import com.sesame.domain.internal.DocMethod
import com.sesame.worker.DocumentationWorker
import org.yaml.snakeyaml.Yaml
import java.util.*

class YamlDocumentationWorkerImpl : DocumentationWorker<Yaml?> {

    override fun processInterfaceMethod(value: Yaml?): Optional<DocMethod> {
        return Optional.empty()
    }

}