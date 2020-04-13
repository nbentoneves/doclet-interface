package com.sesame.core.util

import org.junit.Assert.*
import org.junit.Test

class TextUtilsTest {

    @Test
    fun `should extract valid format`() {
        val configDescription = "@doclib" +
                "test" +
                "info" +
                "@enddoclib"

        val result = TextUtils.extractDocLibTags(configDescription)

        assertTrue(result.isPresent)
        assertEquals("testinfo", result.get())

    }

    @Test
    fun `should not extract invalid format`() {
        val configDescription = "@doc " +
                "test" +
                "info" +
                "@enddoclib"

        val result = TextUtils.extractDocLibTags(configDescription)

        assertFalse(result.isPresent)

    }

    @Test
    fun `should not extract empty values`() {

        val result = TextUtils.extractDocLibTags("")

        assertFalse(result.isPresent)

    }


}