package com.sesame.ui.domain

import org.apache.commons.lang3.builder.ToStringBuilder

/**
 * Request domain represents the object data between application and user interface
 *
 * @author Nuno Bento <nbento.neves@gmail.com>
 */
data class Request(val json: String = "") {

    override fun toString(): String {
        return ToStringBuilder(this)
                .append("json", json)
                .build()
    }

}