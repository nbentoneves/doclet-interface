package com.sesame.core.worker

import com.sesame.core.domain.MethodInfo
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.File
import java.util.*

@Component
class SwitchWorker(private val documentationFactory: DocumentationFactory) {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(SwitchWorker::class.java)
    }

    fun start(configType: String?, configPath: String?): Optional<MethodInfo> {

        if (configPath == null || configPath.isEmpty() || configType == null || configType.isEmpty()) {
            LOGGER.error("Can't process any source. Please check the property inserted.")
            return Optional.empty()
        }

        val worker: DocumentationWorker = when (configType) {
            "TEXT" -> documentationFactory.getTextDocumentationWorker()
            "YAML" -> documentationFactory.getYamlDocumentationWorker()
            else -> {
                LOGGER.info("msg='Can not support this type of configuration', configType='{}'", configType)
                return Optional.empty()
            }
        }

        val methodInfo = worker.processInterfaceMethod(File(configPath))

        if (methodInfo.isPresent) {
            LOGGER.info("{}", methodInfo)
            return methodInfo
        }

        return Optional.empty()
    }

}