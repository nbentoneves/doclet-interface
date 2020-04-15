package com.sesame.core.json.domain

import org.apache.commons.lang3.builder.ToStringBuilder

data class AttrDomain(val isPrimitive: Boolean, val value: Any) {

    override fun toString(): String {
        return ToStringBuilder(this)
                .append("isPrimitive", isPrimitive)
                .append("value", value)
                .build()
    }

}