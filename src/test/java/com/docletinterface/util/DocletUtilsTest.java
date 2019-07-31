package com.docletinterface.util;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class DocletUtilsTest {

    @Test
    public void shouldExtractValidFormat() {
        String commandLine = "@doclib " +
                "test" +
                "info" +
                "@enddoclib";

        Optional<String> result = DocletUtils.extractDoclibTags(commandLine);

        assertTrue(result.isPresent());
        assertEquals(" testinfo", result.get());
    }

    @Test
    public void shouldNotExtractValidFormat() {
        String commandLine = "@doc " +
                "test" +
                "info" +
                "@enddoclib";

        Optional<String> result = DocletUtils.extractDoclibTags(commandLine);

        assertFalse(result.isPresent());
    }

    @Test
    public void shouldNotExtractNullValue() {
        Optional<String> result = DocletUtils.extractDoclibTags(null);
        assertFalse(result.isPresent());
    }

    @Test
    public void shouldNotExtractEmptyValue() {
        Optional<String> result = DocletUtils.extractDoclibTags("");
        assertFalse(result.isPresent());
    }
}
