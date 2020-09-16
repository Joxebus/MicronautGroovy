package io.github.joxebus.micronaut.service

import io.github.joxebus.micronaut.domain.Person
import grails.gorm.services.Service

import javax.validation.Valid
import javax.validation.constraints.NotNull

@Service(Person)
interface PersonService {

    int count()

    Person save(@NotNull @Valid Person person)
    List<Person> findAll()
    Person find(@NotNull Long id)
    void delete(@NotNull Long id)
}
