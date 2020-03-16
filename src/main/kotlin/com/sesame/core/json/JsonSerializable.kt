package com.sesame.core.json

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.lang.reflect.Method

class JsonSerializable {

    companion object {
        private val MAPPING = jacksonObjectMapper()
    }

    fun serialize(classInstance: Any, method: Method, listOfParametersValues: Array<Any>): String = run {
        return try {
            val resultMethod = method.invoke(classInstance, *listOfParametersValues)
            MAPPING.writerWithDefaultPrettyPrinter().writeValueAsString(resultMethod)
        } catch (ex: Exception) {
            MAPPING.writerWithDefaultPrettyPrinter().writeValueAsString(ex)
        }
    }

}