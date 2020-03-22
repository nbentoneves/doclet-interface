package com.sesame.ui

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.context.properties.ConfigurationProperties


@SpringBootApplication(scanBasePackages = [
    "com.sesame.core",
    "com.sesame.ui"
])
open class SesameJavaApplication {

    open fun runService(args: Array<String>) {
        SpringApplicationBuilder()
                .sources(SesameJavaApplication::class.java)
                .build()
                .run(*args)
    }
}

fun main(args: Array<String>) {
    SesameJavaApplication().runService(args)
}
