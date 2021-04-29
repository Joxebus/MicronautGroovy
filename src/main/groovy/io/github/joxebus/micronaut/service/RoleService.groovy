package io.github.joxebus.micronaut.service

import grails.gorm.services.Service
import io.github.joxebus.micronaut.domain.Role

import javax.validation.Valid
import javax.validation.constraints.NotNull

@Service(Role)
interface RoleService {
    int count()
    Role save(@NotNull @Valid Role person)
    List<Role> findAll()
    Role find(@NotNull Long id)
    Role findByName(@NotNull String name)
    void delete(@NotNull Long id)
}