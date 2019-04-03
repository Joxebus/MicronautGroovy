package com.nearsoft.micronaut.controller

import com.nearsoft.micronaut.domain.Person
import com.nearsoft.micronaut.service.PersonService
import groovy.util.logging.Slf4j
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.views.ModelAndView

@Controller("/person")
@Slf4j
class PersonViewController {

    final PersonService personService

    PersonViewController(PersonService personService) {
        this.personService = personService
    }

    @Get("/")
    ModelAndView list(){
        new ModelAndView("person/list",
                [listOfPeople : personService.findAll()]
        )
    }

    @Get("/new")
    ModelAndView showForm(){
        new ModelAndView("person/create",
                [person : new Person()]
        )
    }

    @Post(value = "/create", consumes = "application/x-www-form-urlencoded")
    ModelAndView create(@Body Person person){
        try{
            personService.save(person)
            return new ModelAndView("person/list",
                    [listOfPeople : personService.findAll()]
            )
        }catch(Exception e){
            return new ModelAndView("person/create",
                    [person : person]
            )
        }

    }
}
