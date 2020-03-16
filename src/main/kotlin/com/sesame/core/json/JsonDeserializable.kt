package com.sesame.core.json

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.sesame.domain.internal.ParameterType
import org.slf4j.LoggerFactory
import javax.naming.OperationNotSupportedException
import kotlin.reflect.KClass

//TODO: Refactoring class, we should avoid throw exceptions
class JsonDeserializable {

    companion object {
        private val MAPPING = jacksonObjectMapper()
        private val LOGGER = LoggerFactory.getLogger(JsonDeserializable::class.java)
    }

    fun deserialize(jsonData: String, parametersToMap: Map<Int, ParameterType>): Map<Int, Pair<Any, KClass<Any>>> {

        check(parametersToMap.isNotEmpty()) { "Parameters to map can not be null " }

        val parameterList = mutableMapOf<Int, Pair<Any, KClass<Any>>>()

        val jsonNode: JsonNode?

        try {
            jsonNode = MAPPING.readTree(jsonData)
                    ?: throw DeserializationException("Empty request, please provide a valid request.")
        } catch (ex: JsonParseException) {
            throw DeserializationException("Invalid request! Please verify the request json.", ex)
        }

        if (notMatchNumberOfParameters(jsonNode, parametersToMap)) {
            return mapOf()
        }

        parametersToMap.forEach { entry ->
            run {

                val nodeParameter = jsonNode.get(entry.key - 1)
                        ?: throw DeserializationException("Can't find the correct parameter. Please check the documentation or the request.")

                val isPrimitiveNode = nodeParameter.get("isPrimitive")

                if (isPrimitiveNode != null && isPrimitiveNode.isBoolean) {

                    if (isPrimitiveNode.booleanValue()) {

                        LOGGER.debug("Extract primitive value...")

                        val value = nodeParameter.get("value").asText()
                        val valueWithType = MAPPING.readValue(value, entry.value.internalType)

                        LOGGER.debug("Add new mapping value... valueType={}, value={}", entry.value.internalType, value)
                        parameterList.put(entry.key, Pair(valueWithType, entry.value.internalType.kotlin))

                    } else {
                        throw OperationNotSupportedException("Invalid request! Application not support non primitive values.")
                    }

                } else {
                    throw DeserializationException("Invalid request! Please verify the request json.")
                }
            }
        }

        return parameterList
    }

    private fun notMatchNumberOfParameters(jsonNode: JsonNode, parametersToMap: Map<Int, ParameterType>): Boolean {
        return jsonNode.size() != parametersToMap.size
    }

}