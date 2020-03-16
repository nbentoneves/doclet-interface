package com.sesame.core

import com.sesame.core.json.JsonDeserializable
import com.sesame.core.json.JsonSerializable
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class SesameCoreConfiguration {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(SesameCoreConfiguration::class.java)
    }

    @Bean(name = ["jsonSerializable"])
    open fun getJsonSerializable(): JsonSerializable {
        return JsonSerializable()
    }

    @Bean(name = ["jsonDeserializable"])
    open fun getJsonDeserializable(): JsonDeserializable {
        return JsonDeserializable()
    }

}
