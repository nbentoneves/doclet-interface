package com.sesame.core

import com.sesame.core.json.JsonDeserializable
import com.sesame.core.json.JsonSerializable
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
open class SesameCoreConfiguration {

    @Bean(name = ["jsonSerializable"])
    open fun getJsonSerializable(): JsonSerializable {
        return JsonSerializable()
    }

    @Bean(name = ["jsonDeserializable"])
    open fun getJsonDeserializable(): JsonDeserializable {
        return JsonDeserializable()
    }

    @Bean(name = ["sesameBeanPostProcessorConfiguration"])
    open fun getSesameBeanPostProcessorConfiguration(applicationContext: ApplicationContext): SesameBeanPostProcessorConfiguration {
        return SesameBeanPostProcessorConfiguration(applicationContext)
    }

}