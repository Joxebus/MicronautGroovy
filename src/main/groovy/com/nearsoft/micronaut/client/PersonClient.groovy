package com.nearsoft.micronaut.client

import com.nearsoft.micronaut.api.PersonApi
import io.micronaut.http.client.Client

@Client("/people")
interface PersonClient extends PersonApi {

}
