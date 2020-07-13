package io.github.joxebus.micronaut.client

import io.github.joxebus.micronaut.api.PersonApi
import io.micronaut.http.client.annotation.Client

@Client("/people")
interface PersonClient extends PersonApi {

}
