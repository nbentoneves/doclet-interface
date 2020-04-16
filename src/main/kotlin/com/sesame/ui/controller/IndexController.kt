package com.sesame.ui.controller

import com.sesame.core.Invoker
import com.sesame.core.domain.MethodInfo
import com.sesame.core.json.JsonDeserializable
import com.sesame.core.json.JsonSerializable
import com.sesame.ui.domain.Request
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView

/**
 * Controller for the index page
 *
 * @author Nuno Bento <nbento.neves@gmail.com>
 */
@Controller
class IndexController(private val jsonSerializable: JsonSerializable,
                      private val jsonDeserializable: JsonDeserializable,
                      private val applicationContext: ApplicationContext) {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(IndexController::class.java)
    }

    @Autowired
    lateinit var methodInfo: MethodInfo

    @RequestMapping("/")
    fun index(value: Request): ModelAndView {

        val modelAndView = ModelAndView()
        modelAndView.viewName = "index"
        modelAndView.addObject("packageName", methodInfo.packageName)
        modelAndView.addObject("className", methodInfo.className)
        modelAndView.addObject("methodName", methodInfo.methodName)
        modelAndView.addObject("methodDescription", methodInfo.methodDescription)
        modelAndView.addObject("returnObject", methodInfo.returnObject)
        modelAndView.addObject("paramObjects", methodInfo.paramObjects)
        modelAndView.addObject("errorMessage", "")
        modelAndView.addObject("value", value)

        return modelAndView
    }

    @RequestMapping("/submit", method = [RequestMethod.POST])
    fun processRequest(value: Request): ModelAndView {

        val modelAndView = ModelAndView()
        modelAndView.viewName = "index"
        modelAndView.addObject("packageName", methodInfo.packageName)
        modelAndView.addObject("className", methodInfo.className)
        modelAndView.addObject("methodName", methodInfo.methodName)
        modelAndView.addObject("methodDescription", methodInfo.methodDescription)
        modelAndView.addObject("returnObject", methodInfo.returnObject)
        modelAndView.addObject("paramObjects", methodInfo.paramObjects)
        modelAndView.addObject("value", value)

        if (value.json.isNotBlank()) {
            try {
                modelAndView.addObject("result", Invoker(methodInfo, jsonDeserializable, jsonSerializable, applicationContext).method(value.json).get())
            } catch (ex: Exception) {
                LOGGER.error("message='Something unexpected happened'", ex)
                modelAndView.addObject("errorMessage", "Can't execute the class/method, please check the logging system")
            }
        }

        return modelAndView
    }

}