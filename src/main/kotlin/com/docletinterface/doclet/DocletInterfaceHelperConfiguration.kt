package com.docletinterface.doclet

import com.docletinterface.domain.DocMethod
import com.sun.tools.javadoc.Main
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class DocletInterfaceHelperConfiguration {

    @Value("#{systemProperties.sourceInterface}")
    private val property: String? = null

    @Bean(name = ["docMethod"])
    open fun getDocMethod(): DocMethod {
        Main.execute(*arrayOf("-docletpath", "classes", "-doclet", "com.docletinterface.doclet.GenDocletInterface", property))
        return GenDocletInterface.getDocMethod()
    }

}
