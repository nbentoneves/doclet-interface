package com.sesame.core.worker

import com.sesame.core.domain.MethodInfo
import java.io.File
import java.util.*

/**
 * Interface to create new workers. A worker is a way to load configs at the lib.
 *
 * @author Nuno Bento <nbento.neves@gmail.com>
 */
interface DocumentationWorker {

    fun processInterfaceMethod(file: File): Optional<MethodInfo>

}