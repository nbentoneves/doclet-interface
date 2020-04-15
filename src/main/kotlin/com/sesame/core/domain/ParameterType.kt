package com.sesame.core.domain

import java.util.*

enum class ParameterType(
        val displayType: String,
        val internalType: Class<*>,
        val isPrimitive: Boolean) {

    BOOLEAN("boolean", Boolean::class.java, true),
    CHAR("char", Char::class.java, true),
    BYTE("byte", Byte::class.java, true),
    SHORT("short", Short::class.java, true),
    INT("int", Int::class.java, true),
    LONG("long", Long::class.java, true),
    FLOAT("float", Float::class.java, true),
    DOUBLE("double", Double::class.java, true),
    STRING("string", String::class.java, false),
    OBJECT("object", Object::class.java, false);

    companion object {

        fun getInternalType(displayType: String?): ParameterType {
            return Arrays.stream(values())
                    .filter { parameterType: ParameterType -> parameterType.displayType.equals(displayType, ignoreCase = true) }
                    .findFirst()
                    .orElse(OBJECT)
        }

    }

}