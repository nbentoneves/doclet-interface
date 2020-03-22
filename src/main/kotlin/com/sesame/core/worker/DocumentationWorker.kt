package com.sesame.core.worker

import com.sesame.domain.internal.DocMethod
import java.io.File
import java.util.*

interface DocumentationWorker {

    fun processInterfaceMethod(file: File): Optional<DocMethod>

}