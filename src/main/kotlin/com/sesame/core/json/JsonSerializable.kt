package com.sesame.core.json

import com.google.gson.GsonBuilder
import java.lang.reflect.Method

class JsonSerializable {

    companion object {
        private val MAPPING = GsonBuilder()
                .setPrettyPrinting()
                .create()
    }

    fun serialize(classInstance: Any, method: Method, listOfParametersValues: Array<Any>): String = run {
        return try {
            val resultMethod = method.invoke(classInstance, *listOfParametersValues)
            MAPPING.toJson(resultMethod)
        } catch (ex: Exception) {
            MAPPING.toJson(ex)
        }
    }

}