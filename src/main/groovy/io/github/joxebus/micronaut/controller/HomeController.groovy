package io.github.joxebus.micronaut.controller

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import io.micronaut.views.ModelAndView

@Controller("/")
class HomeController {


    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get("/")
    ModelAndView home(){
        def model = [message: "Hello from micronaut"]
        new ModelAndView("home/home", model)
    }
}
