package com.docletinterface.doclet.logic

import org.junit.Test


class JsonCommandParserTest {


    @Test
    fun methodTest() {
        /*val mapper = ObjectMapper()

        val cazz = Class.forName("com.docletinterface.doclet.domain.Request")

        val value = mapper.readValue("{\"json\":\"test\",\"counter\":10}", cazz)

        println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(value))
*/

        assert(true)
    }

}

class Test {
    var value1: String? = null
    var value2: Double? = null
    var value3: TestExternalClass? = null
}

class TestExternalClass {
    var mappingValues: HashMap<String, String>? = null
}
