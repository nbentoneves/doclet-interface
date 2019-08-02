package com.docletinterface.doclet

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
open class DocletInterfaceHelperApplication

fun main(args: Array<String>) {
    SpringApplication.run(DocletInterfaceHelperApplication::class.java, *args)!!
}
