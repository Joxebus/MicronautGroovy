package io.github.joxebus.micronaut.client

import io.github.joxebus.micronaut.api.PersonApi
import io.github.joxebus.micronaut.domain.Person
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put
import io.micronaut.http.client.annotation.Client

@Client("/people")
interface PersonClient extends PersonApi {

    // Without this 2 methods the test fails cause send a malformed request
    // TODO: need to check why can't just simply call the create(Person) method
    @Post("/")
    Person create(String name, String lastName, int age, String phone)

    @Put("/")
    Person update(Long id, String name, String lastName, int age, String phone)

}
