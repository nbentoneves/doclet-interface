package com.sesame.util;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class TextUtilsTest {

    @Test
    public void shouldExtractValidFormat() {
        String commandLine = "@doclib " +
                "test" +
                "info" +
                "@enddoclib";

        Optional<String> result = TextUtils.extractDoclibTags(commandLine);

        assertTrue(result.isPresent());
        assertEquals(" testinfo", result.get());
    }

    @Test
    public void shouldNotExtractValidFormat() {
        String commandLine = "@doc " +
                "test" +
                "info" +
                "@enddoclib";

        Optional<String> result = TextUtils.extractDoclibTags(commandLine);

        assertFalse(result.isPresent());
    }

    @Test
    public void shouldNotExtractNullValue() {
        Optional<String> result = TextUtils.extractDoclibTags(null);
        assertFalse(result.isPresent());
    }

    @Test
    public void shouldNotExtractEmptyValue() {
        Optional<String> result = TextUtils.extractDoclibTags("");
        assertFalse(result.isPresent());
    }
}
