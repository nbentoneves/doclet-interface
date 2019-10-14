package com.docletinterface.ui.logic

import com.docletinterface.domain.DocMethod
import com.docletinterface.domain.ParameterType
import com.docletinterface.ui.logic.exception.DeserializationException
import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.slf4j.LoggerFactory
import javax.naming.OperationNotSupportedException
import kotlin.reflect.KClass

class InvokeMethod {

    companion object {

        private val MAPPING = jacksonObjectMapper()

        private val LOGGER = LoggerFactory.getLogger(InvokeMethod::class.java)

        fun invoke(metadata: DocMethod, jsondata: String): Any {
            val classInstance = Class.forName(defineClassPath(metadata)).newInstance()

            val parametersMapping = deserialization(jsondata, metadata.paramObjects)
            val listOfParametersType = parametersMapping.map { it.value.java }.toTypedArray()
            val listOfParametersValues = parametersMapping.map { it.key }.toTypedArray()

            LOGGER.debug("List of parameters... listOfParameters={}", listOfParametersType)
            LOGGER.debug("List of parameters... listOfParametersValues={}", listOfParametersValues)

            val method = classInstance!!.javaClass.getMethod(metadata.methodName, *listOfParametersType)

            LOGGER.info("Invoke method... classInstance={}, method={}", classInstance, method)

            val result = serialization(method.invoke(classInstance, *listOfParametersValues))

            LOGGER.info("Result serialized... result={}", result)

            return result
        }

        private fun defineClassPath(metadata: DocMethod) = run { metadata.packageName + "." + metadata.className }

        private fun deserialization(jsonData: String, mappingParameters: Map<Int, ParameterType>): Map<Any, KClass<Any>> {

            val parameterList = mutableMapOf<Any, KClass<Any>>()

            val jsonNode: JsonNode?

            try {
                jsonNode = MAPPING.readTree(jsonData)
                        ?: throw DeserializationException("Invalid request! Please verify the request json.")
            } catch (ex: JsonParseException) {
                throw DeserializationException("Invalid request! Please verify the request json.")
            }

            try {
                mappingParameters.forEach { entry ->
                    run {

                        val nodeParameter = jsonNode.get(entry.key - 1)
                                ?: throw DeserializationException("Can't find the correct parameter. Please check the documentation vs request.")

                        val isPrimitiveNode = nodeParameter.get("isPrimitive")

                        if (isPrimitiveNode != null && isPrimitiveNode.isBoolean) {

                            if (isPrimitiveNode.booleanValue()) {
                                LOGGER.debug("Extract primitive value...")

                                val value = nodeParameter.get("value").asText()
                                val valueTyped = MAPPING.readValue(value, entry.value.internalType)

                                LOGGER.debug("Add new mapping value... parameterType={}, value={}", entry.value, valueTyped)
                                parameterList.put(valueTyped, entry.value.internalType.kotlin)

                            } else {
                                throw OperationNotSupportedException("Invalid request! Application not support non primitive requests.")
                            }

                        } else {
                            throw DeserializationException("Invalid request! Please verify the request json.")
                        }
                    }
                }
            } catch (ex: NoSuchMethodException) {
                throw DeserializationException("Can't find the operation, please check the documentation!", ex)
            } catch (ex: IllegalArgumentException) {
                throw DeserializationException("Can't find the operation, please check the documentation!", ex)
            }

            return parameterList
        }

        private fun serialization(data: Any): String = run { MAPPING.writeValueAsString(data) }

    }

}