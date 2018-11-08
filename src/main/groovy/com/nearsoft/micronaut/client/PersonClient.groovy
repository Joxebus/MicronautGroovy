package com.nearsoft.micronaut.client

import com.nearsoft.micronaut.api.PersonApi
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Delete
import io.micronaut.http.client.annotation.Client

@Client("/people")
interface PersonClient extends PersonApi {
    @Delete("/{id}")
    @Override
    HttpResponse delete(Long id)
}
