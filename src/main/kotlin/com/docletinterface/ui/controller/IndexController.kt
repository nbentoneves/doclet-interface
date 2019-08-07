package com.docletinterface.ui.controller

import com.docletinterface.ui.domain.Request
import com.docletinterface.domain.DocMethod
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView

@Controller
class IndexController {

    @Autowired
    lateinit var docMethod: DocMethod

    @RequestMapping("/")
    fun index(value: Request): ModelAndView {

        val modelAndView = ModelAndView()
        modelAndView.viewName = "index"
        modelAndView.addObject("className", docMethod.className)
        modelAndView.addObject("methodName", docMethod.methodName)
        modelAndView.addObject("methodDescription", docMethod.methodDescription)
        modelAndView.addObject("returnObject", docMethod.returnObject)
        modelAndView.addObject("paramObjects", docMethod.paramObjects)
        modelAndView.addObject("value", value)

        return modelAndView
    }

    @RequestMapping("/submit", method = [RequestMethod.POST])
    fun processRequest(value: Request): ModelAndView {

        val modelAndView = ModelAndView()
        modelAndView.viewName = "index"
        modelAndView.addObject("className", docMethod.className)
        modelAndView.addObject("methodName", docMethod.methodName)
        modelAndView.addObject("methodDescription", docMethod.methodDescription)
        modelAndView.addObject("returnObject", docMethod.returnObject)
        modelAndView.addObject("paramObjects", docMethod.paramObjects)
        modelAndView.addObject("value", value)

        return modelAndView
    }
}