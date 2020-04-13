package com.sesame.core.worker

import com.sesame.core.domain.ParameterType
import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

@RunWith(JUnitParamsRunner::class)
class TextDocumentationWorkerImplTest {

    private val victim = TextDocumentationWorkerImpl()

    @Test
    fun `should verify extract config without prefix`() {

        val config = """
                methodName: startMethod
                methodDescription: Description
                return: String - Description
                param-1: Int - Description
                param-2: XptoObject - Description
                @enddoclib
            """.trimIndent()

        val file = createTemporaryFile(config)

        assertFalse(victim.processInterfaceMethod(file).isPresent)

        file.deleteOnExit()

    }

    @Test
    fun `should verify extract config without suffix`() {

        val config = """
                @doclib
                methodName: startMethod
                methodDescription: Description
                return: String - Description
                param-1: Int - Description
                param-2: XptoObject - Description
            """.trimIndent()

        val file = createTemporaryFile(config)

        assertFalse(victim.processInterfaceMethod(file).isPresent)

        file.deleteOnExit()

    }

    @Test
    fun `should verify extract valid config`() {

        val config = """
                @doclib
                className: className
                packageName: packageName
                methodName: startMethod
                methodDescription: Description
                return: String - Description
                param-1: Int - Description
                param-2: XptoSample - Description
                @enddoclib
            """.trimIndent()

        val file = createTemporaryFile(config)

        val infoMethod = victim.processInterfaceMethod(file)

        assertTrue(infoMethod.isPresent)
        assertEquals("className", infoMethod.get().className)
        assertEquals("packageName", infoMethod.get().packageName)
        assertEquals("startMethod", infoMethod.get().methodName)
        assertEquals("Description", infoMethod.get().methodDescription)
        assertEquals("String", infoMethod.get().returnObject)

        assertEquals(ParameterType.INT, infoMethod.get().paramObjects!![1])
        assertEquals(ParameterType.OBJECT, infoMethod.get().paramObjects!![2])

        file.deleteOnExit()

    }

    //TODO: Review this test
    @Test(expected = RuntimeException::class)
    @Parameters(method = "dataInvalidDocumentation")
    fun `should verify extract invalid config`(methodName: String,
                                               methodDescription: String,
                                               returnInfo: String,
                                               param1: String,
                                               param2: String) {

        val config = """
                @doclib
                $methodName
                $methodDescription
                $returnInfo
                $param1
                $param2
                @enddoclib
            """.trimIndent()

        val file = createTemporaryFile(config)

        val infoMethod = victim.processInterfaceMethod(file)

        assertTrue(infoMethod.isPresent)
        assertEquals("startMethod", infoMethod.get().methodName)
        assertEquals("Description", infoMethod.get().methodDescription)
        assertEquals("String", infoMethod.get().returnObject)

        assertEquals(ParameterType.INT, infoMethod.get().paramObjects!![1])
        assertEquals(ParameterType.OBJECT, infoMethod.get().paramObjects!![2])

        file.deleteOnExit()

    }

    private fun dataInvalidDocumentation() = arrayOf(
            //Invalid methodName format (- instead of :) E.g (methodName - startMethod)
            arrayOf("methodName - startMethod", "methodDescription: Description", "return: String - Description", "param-1: Int - Description", "param-2: XptoObject - Description"),
            //Invalid methodDescription format (- instead of :) E.g (methodDescription - Description)
            arrayOf("methodName: startMethod", "methodDescription - Description", "return: String - Description", "param-1: Int - Description", "param-2: XptoObject - Description"),
            //Invalid return format (- instead of :) E.g (return - String - Description)
            arrayOf("methodName: startMethod", "methodDescription: Description", "return - String - Description", "param-1: Int - Description", "param-2: XptoObject - Description"),
            //Invalid return description format (: instead of -) E.g (return: String : Description)
            arrayOf("methodName: startMethod", "methodDescription: Description", "return: String : Description", "param-1: Int - Description", "param-2: XptoObject - Description"),
            //Invalid param1 format (_ instead of -) E.g (param_1: Int - Description)
            arrayOf("methodName: startMethod", "methodDescription: Description", "return: String - Description", "param_1: Int - Description", "param-2: XptoObject - Description"),
            //Invalid param1 format (- instead of :) E.g (param-1- Int - Description)
            arrayOf("methodName: startMethod", "methodDescription: Description", "return: String - Description", "param-1- Int - Description", "param-2: XptoObject - Description"),
            //Invalid param1 description format (_ instead of -)  E.g (param-1: Int _ Description)
            arrayOf("methodName: startMethod", "methodDescription: Description", "return: String - Description", "param-1: Int _ Description", "param-2: XptoObject - Description"))

    private fun createTemporaryFile(fileContent: String): File {

        val tmpFile = File.createTempFile("tempFile", ".doc")
        val bufferedWriter = BufferedWriter(FileWriter(tmpFile))

        bufferedWriter.write(fileContent)
        bufferedWriter.close()

        return tmpFile

    }

}