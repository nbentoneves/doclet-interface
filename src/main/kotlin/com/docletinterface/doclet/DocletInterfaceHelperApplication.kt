package com.docletinterface.doclet

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
open class DocletInterfaceHelperApplication

fun main(args: Array<String>) {

/*
    val context = Context()
    context.setVariable("title", "User Page")
    context.setVariable("name", "John Doe")

    val writer = FileWriter("hello-thymeleaf.html")
    writer.write(ThymeLeafConfig.getTemplateEngine().process("hello.html", context))
    writer.close()
*/

    SpringApplication.run(DocletInterfaceHelperApplication::class.java, *args)
}
