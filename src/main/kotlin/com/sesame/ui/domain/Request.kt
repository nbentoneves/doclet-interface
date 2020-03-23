package com.sesame.ui.domain

data class Request(val json: String = "") {

    override fun toString(): String {
        return "Request(json='$json')"
    }

}