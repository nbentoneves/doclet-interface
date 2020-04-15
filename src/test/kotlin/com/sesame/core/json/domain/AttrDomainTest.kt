package com.sesame.core.json.domain

import org.hamcrest.Matchers.containsString
import org.hamcrest.core.AllOf.allOf
import org.junit.Assert.*
import org.junit.Test

class AttrDomainTest {

    @Test
    fun `should verify toString method`() {

        val attrDomain = AttrDomain(false, "value")

        assertThat(attrDomain.toString(), allOf(
                containsString("isPrimitive"),
                containsString("value")

        ))

    }

    @Test
    fun `should verify get method`() {

        val attrDomainString = AttrDomain(false, "value")
        val attrDomainInt = AttrDomain(true, 10)

        assertFalse(attrDomainString.isPrimitive)
        assertEquals("value", attrDomainString.value)

        assertTrue(attrDomainInt.isPrimitive)
        assertEquals(10, attrDomainInt.value)

    }

}