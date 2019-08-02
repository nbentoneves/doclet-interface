package com.docletinterface.doclet

import com.docletinterface.doclet.exceptions.DocletInterfaceException
import com.docletinterface.domain.DocMethod
import com.sun.tools.javadoc.Main
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class DocletInterfaceHelperConfiguration {

    @Value("#{systemProperties.sourceInterface}")
    lateinit var property: String

    @Bean(name = ["docMethod"])
    open fun getDocMethod(): DocMethod {
        val result = Main.execute(*arrayOf("-docletpath", "classes", "-doclet", "com.docletinterface.doclet.GenDocletInterface", property))

        if (result == 1) {
            throw DocletInterfaceException("Can not load the javadoc information, please check the command line!")
        }

        return GenDocletInterface.getDocMethod()
    }

}
