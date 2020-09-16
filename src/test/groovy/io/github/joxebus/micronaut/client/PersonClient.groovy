package io.github.joxebus.micronaut.client

import io.github.joxebus.micronaut.domain.Person
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put
import io.micronaut.http.client.annotation.Client

@Client("/people")
interface PersonClient {
    @Post("/")
    Person create(String name, String lastName, int age, String phone)
    @Put("/")
    Person update(Long id, String name, String lastName, int age, String phone)
    @Get("/")
    List<Person> list()
    @Get("/{id}")
    Person show(Long id)
    @Delete("/{id}")
    HttpResponse delete(Long id)
}
