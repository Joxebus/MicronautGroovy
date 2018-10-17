package com.nearsoft.micronaut.api

import com.nearsoft.micronaut.domain.Person
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put

interface PersonApi {

    @Post("/")
    Person create(Person person)
    @Put("/")
    Person update(Person person)
    @Get("/")
    List<Person> list()
    @Get("/{id}")
    Person show(Long id)
    @Delete("/{id}")
    HttpResponse delete(Long id)

}