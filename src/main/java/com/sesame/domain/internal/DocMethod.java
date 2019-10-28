package com.sesame.domain.internal;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class DocMethod {

    private final String packageName;

    private final String className;

    private final String methodName;

    private final String methodDescription;

    private final String returnObject;

    private final String returnObjectDescription;

    private final Map<Integer, ParameterType> paramObjects;

    private DocMethod(Builder builder) {
        this.packageName = builder.packageName;
        this.className = builder.className;
        this.methodName = builder.methodName;
        this.methodDescription = builder.methodDescription;
        this.returnObject = builder.returnObject;
        this.returnObjectDescription = builder.returnObjectDescription;
        this.paramObjects = builder.paramObjects;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getMethodDescription() {
        return methodDescription;
    }

    public String getReturnObject() {
        return returnObject;
    }

    public String getReturnObjectDescription() {
        return returnObjectDescription;
    }

    public Map<Integer, ParameterType> getParamObjects() {
        return paramObjects;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DocMethod.class.getSimpleName() + "[", "]")
                .add("packageName='" + packageName + "'")
                .add("className='" + className + "'")
                .add("methodName='" + methodName + "'")
                .add("methodDescription='" + methodDescription + "'")
                .add("returnObject='" + returnObject + "'")
                .add("returnObjectDescription='" + returnObjectDescription + "'")
                .add("paramObjects=" + paramObjects)
                .toString();
    }

    public static class Builder {

        private String packageName;
        private String className;
        private String methodName;
        private String methodDescription;
        private String returnObject;
        private String returnObjectDescription;
        private Map<Integer, ParameterType> paramObjects = new HashMap<>();

        public Builder withPackageName(String packageName) {
            this.packageName = packageName;
            return this;
        }

        public Builder withClassName(String className) {
            this.className = className;
            return this;
        }

        public Builder withMethodName(String methodName) {
            this.methodName = methodName;
            return this;
        }

        public Builder withMethodDescription(String description) {
            this.methodDescription = description;
            return this;
        }

        public Builder withReturnObject(String returnObject) {
            this.returnObject = returnObject;
            return this;
        }

        public Builder withReturnObjectDescription(String returnObjectDescription) {
            this.returnObjectDescription = returnObjectDescription;
            return this;
        }

        public Builder addParamObjects(int position, ParameterType param) {
            paramObjects.put(position, param);
            return this;
        }

        public DocMethod build() {
            return new DocMethod(this);
        }
    }

}
