package com.sesame.core

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.beans.factory.support.BeanDefinitionRegistry
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader
import org.springframework.context.ApplicationContext

class SesameBeanPostProcessorConfiguration(
        private val applicationContext: ApplicationContext) : BeanDefinitionRegistryPostProcessor {

    companion object {
        private const val PROPERTY_CONFIG_FILE = "config.file.path.application.context"
    }

    override fun postProcessBeanFactory(beanFactory: ConfigurableListableBeanFactory) {
    }

    override fun postProcessBeanDefinitionRegistry(registry: BeanDefinitionRegistry) {
        val xmlBeanDefinitionReader = XmlBeanDefinitionReader(registry)
        val configFile = applicationContext.environment.getProperty("config.file.path.application.context")
                ?: throw RuntimeException("Can not file any value for the property '$PROPERTY_CONFIG_FILE'")

        xmlBeanDefinitionReader.loadBeanDefinitions(configFile)
    }

}