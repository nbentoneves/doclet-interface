package com.sesame.core

import com.sesame.core.domain.MethodInfo
import com.sesame.core.json.JsonDeserializable
import com.sesame.core.json.JsonSerializable
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationContext
import java.util.*

/**
 * This class contains the logic to invoke the class/method declared at config files.
 *
 * @author Nuno Bento <nbento.neves@gmail.com>
 */
class Invoker(private val metadata: MethodInfo,
              private val jsonDeserializable: JsonDeserializable,
              private val jsonSerializable: JsonSerializable,
              private val applicationContext: ApplicationContext) {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(Invoker::class.java)
    }

    fun method(jsonData: String): Optional<Any> {

        val parametersMapping = jsonDeserializable.deserialize(jsonData, metadata.paramObjects)
        val listOfParametersType = parametersMapping.map { it.value.second.java }.toTypedArray()
        val listOfParametersValues = parametersMapping.map { it.value.first }.toTypedArray()

        LOGGER.info("message='List of parameters type', listOfParametersType='{}'", listOfParametersType)
        LOGGER.info("message='List of parameters values', listOfParametersValues='{}'", listOfParametersValues)

        val classInstance: Any = if (metadata.beanIdentification == null)
            Class.forName(defineClassPath(metadata)).newInstance() else applicationContext.getBean(metadata.beanIdentification)

        val method = classInstance::class.java.getMethod(metadata.methodName, *listOfParametersType)

        LOGGER.info("message='Invoke method...', classInstance='{}', method='{}'", classInstance, method)

        val result = jsonSerializable.serialize(classInstance, method, listOfParametersValues)

        LOGGER.info("message='Result method', result='{}'", result)

        return Optional.of(result)

    }

    private fun defineClassPath(metadata: MethodInfo) = run { metadata.packageName + "." + metadata.className }

}