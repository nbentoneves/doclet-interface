package com.sesame.domain.internal;

import java.util.Arrays;

public enum ParameterType {

    INT("int", Integer.class),
    BOOLEAN("boolean", Boolean.class),
    OBJECT("object", Object.class);

    private String displayType;
    private Class internalType;

    ParameterType(String displayType, Class internalType) {
        this.displayType = displayType;
        this.internalType = internalType;
    }

    public String getDisplayType() {
        return this.displayType;
    }

    public Class getInternalType() {
        return this.internalType;
    }

    public static ParameterType getInternalType(String displayType) {

        return Arrays.stream(ParameterType.values())
                .filter(parameterType -> parameterType.displayType.equalsIgnoreCase(displayType))
                .findFirst()
                .orElse(OBJECT);

    }
}
