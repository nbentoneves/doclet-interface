package com.sesame.core.json

import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.MalformedJsonException
import com.sesame.core.domain.ParameterType
import com.sesame.core.json.domain.AttrDomain
import org.slf4j.LoggerFactory
import kotlin.reflect.KClass

class JsonDeserializable {

    companion object {
        private val GSON = GsonBuilder().create()
        private val LOGGER = LoggerFactory.getLogger(JsonDeserializable::class.java)
    }

    //TODO: Explore this:
    // https://medium.com/@imstudio/android-kotlin-how-to-use-typetoken-generics-with-gson-in-kotlin-e2b17c0ac24c
    private inline fun <reified T> fromJson(json: String): T {
        return GSON.fromJson(json, object : TypeToken<T>() {}.type)
    }

    fun deserialize(jsonData: String, parametersToMap: Map<Int, ParameterType>): Map<Int, Pair<Any, KClass<*>>> {

        check(parametersToMap.isNotEmpty()) { "parametersToMap can not be null or empty" }
        check(jsonData.isNotBlank()) { "jsonData can not be null or empty" }

        val parameterList = mutableMapOf<Int, Pair<Any, KClass<*>>>()

        try {
            val jsonDomain: List<AttrDomain> = GSON.fromJson(jsonData, object : TypeToken<List<AttrDomain>>() {}.type)

            if (notMatchNumberOfParameters(jsonDomain, parametersToMap)) {
                return mapOf()
            }

            parametersToMap.forEach { entry ->
                run {

                    val jsonAttr = jsonDomain[entry.key - 1]

                    if (jsonAttr.isPrimitive) {

                        LOGGER.debug("message='Extracting primitive value...'")

                        val valueWithType = mappingParameters(jsonAttr, entry.value)

                        LOGGER.info("message='Add new mapping value' valueType='{}', value='{}'", entry.value.internalType, jsonAttr.value)
                        parameterList[entry.key] = Pair(valueWithType, entry.value.internalType.kotlin)

                    } else {
                        throw UnsupportedOperationException("This type of operation is not supported")
                    }

                }
            }

        } catch (ex: MalformedJsonException) {
            LOGGER.error("message='Invalid json', jsonData='{}'", jsonData)
        } catch (ex: JsonSyntaxException) {
            LOGGER.error("message='Invalid json', jsonData='{}'", jsonData)
        } catch (ex: IndexOutOfBoundsException) {
            LOGGER.error("message='The parameters mapping missing some information', parametersToMap='{}'", parametersToMap)
        }

        return parameterList
    }


    private fun mappingParameters(jsonAttr: AttrDomain, parameterType: ParameterType) =
            when (parameterType) {
                ParameterType.STRING -> jsonAttr.value as String
                ParameterType.INT -> (jsonAttr.value as Double).toInt()
                ParameterType.FLOAT -> (jsonAttr.value as Double).toFloat()
                ParameterType.LONG -> (jsonAttr.value as Double).toLong()
                ParameterType.SHORT -> (jsonAttr.value as Double).toShort()
                ParameterType.BYTE -> (jsonAttr.value as Double).toByte()
                ParameterType.CHAR -> (jsonAttr.value as String)[0]
                ParameterType.BOOLEAN -> jsonAttr.value as Boolean
                ParameterType.DOUBLE -> jsonAttr.value as Double
                else -> DeserializationException("Not support the parameter type inserted")
            }

    private fun notMatchNumberOfParameters(jsonDomain: List<AttrDomain>, parametersToMap: Map<Int, ParameterType>): Boolean {
        return jsonDomain.size != parametersToMap.size
    }

}