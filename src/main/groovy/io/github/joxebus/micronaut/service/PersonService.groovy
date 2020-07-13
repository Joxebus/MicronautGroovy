package io.github.joxebus.micronaut.service


import grails.gorm.services.Service
import io.github.joxebus.micronaut.domain.Person

import javax.validation.constraints.NotNull

@Service(Person)
interface PersonService {

    int count()

    Person save(@NotNull Person person)
    List<Person> findAll()
    Person find(@NotNull Long id)
    void delete(@NotNull Long id)
}
