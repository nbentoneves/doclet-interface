package com.sesame.core.worker

import com.sesame.domain.internal.DocMethod
import com.sesame.worker.DocumentationFactory
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.File
import java.util.*

@Component
class SwitchWorker(private val documentationFactory: DocumentationFactory) {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(SwitchWorker::class.java)
    }

    fun start(configType: String?, configPath: String?): Optional<DocMethod> {

        if (configPath == null || configPath.isEmpty() || configType == null || configType.isEmpty()) {
            LOGGER.error("Can't process any source. Please check the property inserted.")
            return Optional.empty()
        }

        val worker: DocumentationWorker = when (configType) {
            "TEXT" -> documentationFactory.textDocumentationWorker
            "YAML" -> documentationFactory.yamlDocumentationWorker
            else -> {
                LOGGER.info("msg='Can not support this type of configuration', configType='{}'", configType)
                return Optional.empty()
            }
        }

        val docMethod = worker.processInterfaceMethod(File(configPath))

        if (docMethod.isPresent) {
            LOGGER.info("{}", docMethod)
            return docMethod
        }

        return Optional.empty()
    }

}