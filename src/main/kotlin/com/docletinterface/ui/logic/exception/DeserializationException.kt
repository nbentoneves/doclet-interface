package com.docletinterface.ui.logic.exception

import com.docletinterface.ui.DocletInterfaceException

class DeserializationException : DocletInterfaceException{

    constructor(message: String, ex: Exception?) : super(message, ex)
    constructor(message: String) : super(message)

}