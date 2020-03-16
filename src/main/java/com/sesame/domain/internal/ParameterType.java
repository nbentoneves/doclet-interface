package com.sesame.domain.internal;

import java.util.Arrays;

public enum ParameterType {

    INT("int", int.class, true),
    DOUBLE("double", double.class, true),
    INTEGER("integer", Integer.class, false),
    STRING("string", String.class, false),
    BOOLEAN("boolean", boolean.class, true),
    OBJECT("object", Object.class, false);

    private String displayType;
    private Class internalType;
    private boolean isPrimitive;

    ParameterType(String displayType, Class internalType, boolean isPrimitive) {
        this.displayType = displayType;
        this.internalType = internalType;
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
