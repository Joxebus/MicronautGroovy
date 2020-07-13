package io.github.joxebus.micronaut.controller

import io.github.joxebus.micronaut.api.PersonApi
import io.github.joxebus.micronaut.domain.Person
import io.github.joxebus.micronaut.service.PersonService
import groovy.transform.CompileStatic
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put

@Controller("/people")
@CompileStatic
class PersonController implements PersonApi {

    final PersonService personService

    PersonController(PersonService personService){
        this.personService = personService
    }

    @Override
    @Post("/")
    Person create(@Body Person person){
        personService.save(person)
    }

    @Override
    @Put("/")
    Person update(@Body Person person){
        personService.save(person)
    }

    @Override
    @Get("/")
    List<Person> list() {
        personService.findAll()
    }

    @Override
    @Get("/{id}")
    Person show(Long id) {
        personService.find(id)
    }

    @Override
    HttpResponse delete(Long id) {
        personService.delete(id)
        HttpResponse.ok()
    }
}
