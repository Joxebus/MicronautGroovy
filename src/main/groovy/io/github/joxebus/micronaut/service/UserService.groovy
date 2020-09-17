package io.github.joxebus.micronaut.service

import grails.gorm.services.Service
import io.github.joxebus.micronaut.domain.User

import javax.validation.Valid
import javax.validation.constraints.NotNull

@Service(User)
interface UserService {
    int count()
    User save(@NotNull @Valid User person)
    List<User> findAll()
    User find(@NotNull Long id)
    User findByUsername(@NotNull String username)
    void delete(@NotNull Long id)
}