package com.sesame.ui.domain

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.containsString
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

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