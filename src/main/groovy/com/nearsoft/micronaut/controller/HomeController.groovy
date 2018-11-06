package com.nearsoft.micronaut.controller

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.views.ModelAndView

@Controller("/")
class HomeController {


    @Get("/")
    ModelAndView home(){
        def model = [message: "Hello from micronaut"]
        new ModelAndView("home/home", model)
    }
}
