package com.sesame.core.domain

import org.apache.commons.lang3.builder.ToStringBuilder

class MethodInfo private constructor(
        val beanIdentification: String?,
        val packageName: String?,
        val className: String?,
        val methodName: String?,
        val methodDescription: String?,
        val returnObject: String?,
        val returnObjectDescription: String?,
        val paramObjects: Map<Int, ParameterType>) {


    data class Builder(
            private var beanIdentification: String? = null,
            private var packageName: String? = null,
            private var className: String? = null,
            private var methodName: String? = null,
            private var methodDescription: String? = null,
            private var returnObject: String? = null,
            private var returnObjectDescription: String? = null,
            private val paramObjects: MutableMap<Int, ParameterType> = mutableMapOf()) {

        fun withBeanIdentification(beanIdentification: String) = apply { this.beanIdentification = beanIdentification }
        fun withPackageName(packageName: String) = apply { this.packageName = packageName }
        fun withClassName(className: String) = apply { this.className = className }
        fun withMethodName(methodName: String) = apply { this.methodName = methodName }
        fun withMethodDescription(methodDescription: String) = apply { this.methodDescription = methodDescription }
        fun withReturnObject(returnObject: String) = apply { this.returnObject = returnObject }
        fun withReturnObjectDescription(returnObjectDescription: String) = apply { this.returnObjectDescription = returnObjectDescription }
        fun addParamObjects(position: Int, param: ParameterType) = apply { this.paramObjects[position] = param }

        fun build() = MethodInfo(beanIdentification,
                packageName,
                className,
                methodName,
                methodDescription,
                returnObject,
                returnObjectDescription,
                paramObjects)
    }

    override fun toString(): String {
        return ToStringBuilder(this)
                .append("beanIdentification", beanIdentification)
                .append("packageName", packageName)
                .append("className", className)
                .append("methodName", methodName)
                .append("methodDescription", methodDescription)
                .append("returnObject", returnObject)
                .append("returnObjectDescription", returnObjectDescription)
                .append("paramObjects", paramObjects)
                .build()
    }
}
