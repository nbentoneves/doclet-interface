package com.docletinterface.doclet

import org.thymeleaf.TemplateEngine
import org.thymeleaf.templateresolver.FileTemplateResolver

//https://jgalacambra.wordpress.com/2016/06/08/writing-a-file-using-thymeleaf/
enum class ThymeLeafConfig private constructor() {
    INSTANCE;

    private val templateEngine: TemplateEngine

    private val templatePath: String
        get() = ThymeLeafConfig::class.java.protectionDomain.codeSource.location.path + "static/"

    init {
        val templateResolver = FileTemplateResolver()
        templateResolver.prefix = templatePath
        templateResolver.setTemplateMode("HTML5")
        templateEngine = TemplateEngine()
        templateEngine.setTemplateResolver(templateResolver)
    }

    companion object {

        fun getTemplateEngine(): TemplateEngine {
            return INSTANCE.templateEngine
        }
    }
}