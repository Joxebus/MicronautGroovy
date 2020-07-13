package io.github.joxebus.micronaut.api


import io.github.joxebus.micronaut.domain.Person
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put

interface PersonApi {

    @Post("/")
    Person create(@Body Person person)
    @Put("/")
    Person update(@Body Person person)
    @Get("/")
    List<Person> list()
    @Get("/{id}")
    Person show(Long id)
    @Delete("/{id}")
    HttpResponse delete(Long id)

}
