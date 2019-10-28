package com.sesame.ui

import com.sesame.worker.GenDocletInterface
import com.sesame.domain.internal.DocMethod
import com.sun.tools.javadoc.Main
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class SesameJavaConfiguration {

    @Value("\${doclet.sourceInterface}")
    lateinit var property: String

    @Bean(name = ["docMethod"])
    open fun getDocMethod(): DocMethod {
        val result = Main.execute(*arrayOf("-docletpath", "classes", "-doclet", "com.sesame.worker.GenDocletInterface", property))

        if (result == 1) {
            throw SesameJavaException("Can not load the javadoc information, please check the command line!")
        }

        return GenDocletInterface.getDocMethod()
    }

}
