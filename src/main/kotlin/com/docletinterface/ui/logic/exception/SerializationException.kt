package com.docletinterface.ui.logic

import com.docletinterface.ui.DocletInterfaceException

class SerializationException : DocletInterfaceException{

    constructor(message: String, ex: Exception?) : super(message, ex)
    constructor(message: String) : super(message)

}