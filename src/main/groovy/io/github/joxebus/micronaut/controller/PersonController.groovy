package io.github.joxebus.micronaut.controller

import io.github.joxebus.micronaut.domain.Person
import io.github.joxebus.micronaut.service.PersonService
import groovy.transform.CompileStatic
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put

@Controller("/people")
@CompileStatic
class PersonController {

    final PersonService personService

    PersonController(PersonService personService){
        this.personService = personService
    }

    @Post("/")
    Person create(@Body Person person){
        personService.save(person)
    }

    @Put("/")
    Person update(@Body Person person){
        personService.save(person)
    }

    @Get("/")
    List<Person> list() {
        personService.findAll()
    }

    @Get("/{id}")
    Person show(Long id) {
        personService.find(id)
    }

    @Delete("/{id}")
    HttpResponse delete(Long id) {
        personService.delete(id)
        HttpResponse.ok()
    }
}
