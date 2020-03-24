package com.sesame.ui.domain

/**
 * Request domain represents the object data between application and user interface
 *
 * @author Nuno Bento <nbento.neves@gmail.com>
 */
data class Request(val json: String = "") {

    override fun toString(): String {
        return "Request(json='$json')"
    }

}