package com.sesame.domain.internal;

import java.util.Arrays;

public enum ParameterType {

    BOOLEAN("boolean", boolean.class, true),
    CHAR("char", char.class, true),
    BYTE("byte", byte.class, true),
    SHORT("short", short.class, true),
    INT("int", int.class, true),
    LONG("long", long.class, true),
    FLOAT("float", float.class, true),
    DOUBLE("double", double.class, true),
    STRING("string", String.class, false),
    OBJECT("object", Object.class, false);

    private String displayType;
    private Class internalType;
    private boolean isPrimitive;

    ParameterType(String displayType, Class internalType, boolean isPrimitive) {
        this.displayType = displayType;
        this.internalType = internalType;
        this.isPrimitive = isPrimitive;
    }

    public String getDisplayType() {
        return this.displayType;
    }

    public Class getInternalType() {
        return this.internalType;
    }

    public boolean isPrimitive() {
        return isPrimitive;
    }

    public static ParameterType getInternalType(String displayType) {

        return Arrays.stream(ParameterType.values())
                .filter(parameterType -> parameterType.displayType.equalsIgnoreCase(displayType))
                .findFirst()
                .orElse(OBJECT);

    }
}
