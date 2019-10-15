package com.docletinterface.ui

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder


@SpringBootApplication
open class DocletInterfaceHelperApplication {

    open fun runService(args: Array<String>) {
        SpringApplicationBuilder()
                .sources(DocletInterfaceHelperApplication::class.java)
                .build()
                .run(*args)
    }
}

fun main(args: Array<String>) {
    DocletInterfaceHelperApplication().runService(args)
}
