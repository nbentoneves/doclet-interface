package com.docletinterface.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class DocMethod {

    private final String className;

    private final String methodName;

    private final String methodDescription;

    private final String returnObject;

    private final Map<Integer, String> paramObjects;

    private DocMethod(Builder builder) {
        this.className = builder.className;
        this.methodName = builder.methodName;
        this.methodDescription = builder.methodDescription;
        this.returnObject = builder.returnObject;
        this.paramObjects = builder.paramObjects;
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

    public Map<Integer, String> getParamObjects() {
        return paramObjects;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DocMethod.class.getSimpleName() + "[", "]")
                .add("className='" + className + "'")
                .add("methodName='" + methodName + "'")
                .add("methodDescription='" + methodDescription + "'")
                .add("returnObject='" + returnObject + "'")
                .add("paramObjects=" + paramObjects)
                .toString();
    }

    public static class Builder {

        private String className;
        private String methodName;
        private String methodDescription;
        private String returnObject;
        private Map<Integer, String> paramObjects = new HashMap<>();

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

        public Builder addParamObjects(int position, String param) {
            paramObjects.put(position, param);
            return this;
        }

        public DocMethod build() {
            return new DocMethod(this);
        }
    }

}
