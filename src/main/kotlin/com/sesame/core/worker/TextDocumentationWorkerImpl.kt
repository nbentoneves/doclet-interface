package com.sesame.core.worker

import com.sesame.core.domain.MethodInfo
import com.sesame.core.domain.ParameterType
import com.sesame.core.util.TextUtils
import org.apache.commons.io.IOUtils
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.File
import java.io.IOException
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import java.util.regex.PatternSyntaxException

@Component
class TextDocumentationWorkerImpl : DocumentationWorker {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(TextDocumentationWorkerImpl::class.java)

        private const val DELIMITATOR_COLON = ":"
        private const val DELIMITATOR_DASH = "-"

        private const val FIELD_BEAN_IDENTIFICATION = "beanIdentification"
        private const val FIELD_PACKAGE_NAME = "packageName"
        private const val FIELD_CLASSNAME = "className"
        private const val FIELD_METHOD_NAME = "methodName"
        private const val FIELD_METHOD_DESCRIPTION = "methodDescription"
        private const val FIELD_RETURN = "return"
        private const val FIELD_PARAM = "param"

    }

    private enum class Tag {
        BEAN_IDENTIFICATION_NAME,
        PACKAGE_NAME,
        CLASS_NAME,
        METHOD_NAME,
        METHOD_DESCRIPTION,
        RETURN,
        PARAM,
        OTHER
    }

    override fun processInterfaceMethod(file: File): Optional<MethodInfo> {

        try {

            val configs = IOUtils.toString(Files.newInputStream(Paths.get(file.absolutePath)), Charset.defaultCharset())

            val filterDocumentation = TextUtils.extractDocLibTags(configs)

            if (!filterDocumentation.isPresent) {
                return Optional.empty()
            }

            val docLines = filterDocumentation.get().split("\n".toRegex())

            val methodInfo = MethodInfo.Builder()

            for (docLine in docLines) {
                val line = docLine.trim()
                try {
                    when (getTag(line)) {
                        Tag.BEAN_IDENTIFICATION_NAME -> {
                            val beanIdentification = line.split(DELIMITATOR_COLON.toRegex())[1].trim()
                            methodInfo.withBeanIdentification(beanIdentification)
                        }

                        Tag.PACKAGE_NAME -> {
                            val packageName = line.split(DELIMITATOR_COLON.toRegex())[1].trim()
                            methodInfo.withPackageName(packageName)
                        }

                        Tag.CLASS_NAME -> {
                            val className = line.split(DELIMITATOR_COLON.toRegex())[1].trim()
                            methodInfo.withClassName(className)
                        }

                        Tag.METHOD_NAME -> {
                            val methodName = line.split(DELIMITATOR_COLON.toRegex())[1].trim()
                            methodInfo.withMethodName(methodName)
                        }

                        Tag.METHOD_DESCRIPTION -> {
                            val methodDescrip = line.split(DELIMITATOR_COLON.toRegex())[1].trim()
                            methodInfo.withMethodDescription(methodDescrip)
                        }

                        Tag.RETURN -> {
                            val returnDocumentation = line.split(DELIMITATOR_COLON.toRegex())[1].trim()
                            try {
                                val returnDescription = returnDocumentation.split(DELIMITATOR_DASH.toRegex())[1].trim()
                                val returnType = returnDocumentation.split(DELIMITATOR_DASH.toRegex())[0].trim()
                                methodInfo.withReturnObject(returnType)
                                methodInfo.withReturnObjectDescription(returnDescription)
                            } catch (ex: PatternSyntaxException) {
                                LOGGER.warn("message='Can not extract the value detail, check the format of documentation', return='{}'", returnDocumentation, ex)
                                methodInfo.withReturnObject(returnDocumentation)
                            }
                        }

                        Tag.PARAM -> {
                            val paramDocumentation = line.split(DELIMITATOR_COLON.toRegex())[1].trim()
                            val paramIndex = line.split(DELIMITATOR_COLON.toRegex())[0].split(DELIMITATOR_DASH.toRegex())[1].toInt()
                            try {
                                val paramDescription = paramDocumentation.split(DELIMITATOR_DASH.toRegex())[1].trim()
                                val paramType = paramDocumentation.split(DELIMITATOR_DASH.toRegex())[0].trim()
                                methodInfo.addParamObjects(paramIndex, ParameterType.getInternalType(paramType))
                            } catch (ex: PatternSyntaxException) {
                                LOGGER.warn("message='Can not extract the param detail, check the format of documentation', param='{}'", paramDocumentation, ex)
                            }
                        }
                        else -> {
                        }
                    }
                } catch (ex: Exception) {
                    LOGGER.error("message='Can not extract the value, check the format of documentation', docLine='{}'", docLine, ex)
                    throw RuntimeException("Error when try to extract the values from line")
                }
            }

            return Optional.of(methodInfo.build())

        } catch (ex: IOException) {
            LOGGER.error("message='Can not extract the value from config variable.'", ex)
            throw RuntimeException("Error when try to extract the values from line")
        }

    }

    private fun getTag(line: String): Tag {

        when {
            line.startsWith(FIELD_METHOD_NAME) -> {
                return Tag.METHOD_NAME
            }
            line.startsWith(FIELD_METHOD_DESCRIPTION) -> {
                return Tag.METHOD_DESCRIPTION
            }
            line.startsWith(FIELD_RETURN) -> {
                return Tag.RETURN
            }
            line.startsWith(FIELD_PARAM) -> {
                return Tag.PARAM
            }
            line.startsWith(FIELD_CLASSNAME) -> {
                return Tag.CLASS_NAME
            }
            line.startsWith(FIELD_PACKAGE_NAME) -> {
                return Tag.PACKAGE_NAME
            }
            line.startsWith(FIELD_BEAN_IDENTIFICATION) -> {
                return Tag.BEAN_IDENTIFICATION_NAME
            }
            else -> return Tag.OTHER
        }

    }

}