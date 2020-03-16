package com.sesame.core.test;

import org.jetbrains.annotations.NotNull;

public class TestClass {

    public int method(int a, int b) {
        return a + b;
    }

    public Integer method(Integer a, Integer b) {
        return a + b;
    }

    public boolean method(@NotNull String value) {
        return value.isEmpty();
    }


}
