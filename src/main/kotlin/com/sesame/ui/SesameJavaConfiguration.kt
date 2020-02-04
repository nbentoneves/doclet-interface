package com.sesame.ui

import com.sesame.domain.internal.DocMethod
import com.sesame.ui.logic.InvokeMethod
import com.sesame.worker.*
import org.apache.commons.io.IOUtils
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.nio.charset.Charset

@Configuration
open class SesameJavaConfiguration {

    private val LOGGER = LoggerFactory.getLogger(SesameJavaConfiguration::class.java)

    @Value("\${source.config.file}")
    lateinit var property: String

    @Bean(name = ["docMethod"])
    open fun getDocMethod(genDocletInterface: GenDocletInterface): DocMethod {

        LOGGER.info("Path of source config file {}", property)

        val configInfo = IOUtils.toString(javaClass.classLoader.getResourceAsStream(property), Charset.defaultCharset())

        val result = genDocletInterface.start(configInfo)

        if (!result) {
            throw SesameJavaException("Can not load the javadoc information, please check the command line!")
        }

        return genDocletInterface.docMethod
    }

    @Bean(name = ["genDocletInterface"])
    open fun getGenDocletInterface(documentationFactory: DocumentationFactory): GenDocletInterface {
        return GenDocletInterface(documentationFactory)
    }

    @Bean(name = ["documentationFactory"])
    open fun getDocumentationFactory(javaDocsDocumentationWorker: JavaDocsDocumentationWorkerImpl,
                                     yamlDocumentationWorkerImpl: YamlDocumentationWorkerImpl): DocumentationFactory {
        return DocumentationFactoryImpl(javaDocsDocumentationWorker, yamlDocumentationWorkerImpl)
    }

    @Bean(name = ["javaDocsDocumentationWorker"])
    open fun getJavaDocsDocumentationWorker(): JavaDocsDocumentationWorkerImpl {
        return JavaDocsDocumentationWorkerImpl()
    }

    @Bean(name = ["yamlDocsDocumentationWorker"])
    open fun getYamlDocsDocumentationWorker(): YamlDocumentationWorkerImpl {
        return YamlDocumentationWorkerImpl()
    }

}
