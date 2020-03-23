package com.sesame.ui.domain

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.containsString
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class RequestTest {

    @Test
    fun `verify toString method`() {

        val request = Request("JSON_DATA")

        assertEquals("JSON_DATA", request.json)
        assertThat(request.toString(), containsString("json='JSON_DATA'"))

    }

    @Test
    fun `verify equals method`() {

        val request = Request("JSON_DATA")
        val requestOther = Request("JSON_DATA")
        val requestDifferent = Request("JSON_OTHER")

        assertEquals(request, requestOther)
        assertNotEquals(request, requestDifferent)

    }

}