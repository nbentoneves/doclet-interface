package com.sesame.ui

/**
 * Generic Exception
 *
 * @author Nuno Bento <nbento.neves@gmail.com>
 */
open class SesameJavaException : RuntimeException {

    constructor(message: String, ex: Exception?) : super(message, ex)
    constructor(message: String) : super(message)

}