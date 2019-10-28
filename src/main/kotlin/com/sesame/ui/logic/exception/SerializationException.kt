package com.sesame.ui.logic.exception

import com.sesame.ui.SesameJavaException

class SerializationException : SesameJavaException{

    constructor(message: String, ex: Exception?) : super(message, ex)
    constructor(message: String) : super(message)

}