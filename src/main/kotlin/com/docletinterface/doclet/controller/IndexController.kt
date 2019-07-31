package com.docletinterface.doclet.controller

import com.docletinterface.domain.DocMethod
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class IndexController {

    @Autowired
    lateinit var docMethod: DocMethod

    @RequestMapping("/")
    fun index(): String {
        return "index"
    }
}