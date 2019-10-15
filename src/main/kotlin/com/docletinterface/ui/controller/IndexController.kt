package com.docletinterface.ui.controller

import com.docletinterface.domain.DocMethod
import com.docletinterface.ui.DocletInterfaceException
import com.docletinterface.ui.domain.Request
import com.docletinterface.ui.logic.InvokeMethod
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView

@Controller
class IndexController {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(IndexController::class.java)
    }

    @Autowired
    lateinit var docMethod: DocMethod

    @RequestMapping("/")
    fun index(value: Request): ModelAndView {

        val modelAndView = ModelAndView()
        modelAndView.viewName = "index"
        modelAndView.addObject("packageName", docMethod.packageName)
        modelAndView.addObject("className", docMethod.className)
        modelAndView.addObject("methodName", docMethod.methodName)
        modelAndView.addObject("methodDescription", docMethod.methodDescription)
        modelAndView.addObject("returnObject", docMethod.returnObject)
        modelAndView.addObject("paramObjects", docMethod.paramObjects)
        modelAndView.addObject("errorMsg", "")
        modelAndView.addObject("value", value)

        return modelAndView
    }

    @RequestMapping("/submit", method = [RequestMethod.POST])
    fun processRequest(value: Request): ModelAndView {

        val modelAndView = ModelAndView()
        modelAndView.viewName = "index"
        modelAndView.addObject("packageName", docMethod.packageName)
        modelAndView.addObject("className", docMethod.className)
        modelAndView.addObject("methodName", docMethod.methodName)
        modelAndView.addObject("methodDescription", docMethod.methodDescription)
        modelAndView.addObject("returnObject", docMethod.returnObject)
        modelAndView.addObject("paramObjects", docMethod.paramObjects)
        modelAndView.addObject("value", value)

        if (value.json != null) {
            try {
                modelAndView.addObject("result", InvokeMethod.invoke(docMethod, value.json!!))
            } catch (ex: DocletInterfaceException) {
                LOGGER.error("Can't call the method because of: ", ex)
                modelAndView.addObject("errorMsg", ex.message)
            }
        }

        return modelAndView
    }
}