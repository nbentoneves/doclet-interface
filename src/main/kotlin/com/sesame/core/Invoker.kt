package com.sesame.core

import com.sesame.core.json.JsonDeserializable
import com.sesame.core.json.JsonSerializable
import com.sesame.domain.internal.DocMethod
import com.sesame.ui.SesameJavaException
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationContext
import java.util.*

class Invoker(private val metadata: DocMethod,
              private val jsonDeserializable: JsonDeserializable,
              private val jsonSerializable: JsonSerializable,
              private val applicationContext: ApplicationContext) {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(Invoker::class.java)
    }

    fun method(jsonData: String): Optional<Any> {

        try {

            val parametersMapping = jsonDeserializable.deserialize(jsonData, metadata.paramObjects)

            if (parametersMapping.isNotEmpty()) {

                val listOfParametersType = parametersMapping.map { it.value.second.java }.toTypedArray()
                val listOfParametersValues = parametersMapping.map { it.value.first }.toTypedArray()

                LOGGER.debug("List of parameters type... listOfParametersType={}", listOfParametersType)
                LOGGER.debug("List of parameters values... listOfParametersValues={}", listOfParametersValues)

                if (listOfParametersType.isNotEmpty() && listOfParametersValues.isNotEmpty()) {

                    val classInstance: Any = applicationContext.getBean(metadata.beanIdentification)

                    val method = classInstance::class.java.getMethod(metadata.methodName, *listOfParametersType)

                    LOGGER.info("Invoke method... classInstance={}, method={}", classInstance, method)

                    val result = jsonSerializable.serialize(classInstance, method, listOfParametersValues)

                    LOGGER.info("Result serialized... result={}", result)

                    return Optional.of(result)

                }

            }

        } catch (ex: Exception) {
            throw SesameJavaException("Can't execute the class/method, please check the configuration files or javadocs", ex)
        }

        return Optional.empty()

    }

}