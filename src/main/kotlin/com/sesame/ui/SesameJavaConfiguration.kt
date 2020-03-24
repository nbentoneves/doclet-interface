package com.sesame.ui

import com.sesame.core.worker.SwitchWorker
import com.sesame.core.worker.YamlDocumentationWorkerImpl
import com.sesame.domain.internal.DocMethod
import com.sesame.worker.DocumentationFactory
import com.sesame.worker.DocumentationFactoryImpl
import com.sesame.worker.TextDocumentationWorkerImpl
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * This is a sesame java configuration for UI
 * TODO: Move all beans for core to {@link com.sesame.core} package
 *
 * @author Nuno Bento <nbento.neves@gmail.com>
 */
@Configuration
open class SesameJavaConfiguration {

    private val LOGGER = LoggerFactory.getLogger(SesameJavaConfiguration::class.java)

    @Value("\${config.file.path}")
    lateinit var property: String

    @Value("\${config.type}")
    lateinit var configType: String

    @Bean(name = ["docMethod"])
    open fun getDocMethod(switchWorker: SwitchWorker): DocMethod {

        LOGGER.info("Path of source config file: {}", property)

        val result = switchWorker.start(configType, property)

        if (!result.isPresent) {
            throw SesameJavaException("Can not load the javadoc information, please check the command line!")
        }

        return result.get()
    }

    @Bean(name = ["generateDocumentation"])
    open fun getGenerateDocumentation(documentationFactory: DocumentationFactory): SwitchWorker {
        return SwitchWorker(documentationFactory)
    }

    @Bean(name = ["documentationFactory"])
    open fun getDocumentationFactory(textDocumentationWorker: TextDocumentationWorkerImpl,
                                     yamlDocumentationWorkerImpl: YamlDocumentationWorkerImpl): DocumentationFactory {
        return DocumentationFactoryImpl(textDocumentationWorker, yamlDocumentationWorkerImpl)
    }

    @Bean(name = ["textDocumentationWorker"])
    open fun getTextDocumentationWorker(): TextDocumentationWorkerImpl {
        return TextDocumentationWorkerImpl()
    }

    @Bean(name = ["yamlDocsDocumentationWorker"])
    open fun getYamlDocsDocumentationWorker(): YamlDocumentationWorkerImpl {
        return YamlDocumentationWorkerImpl()
    }

}
