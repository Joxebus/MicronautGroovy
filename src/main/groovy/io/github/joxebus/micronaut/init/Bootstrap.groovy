package io.github.joxebus.micronaut.init


import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import io.github.joxebus.micronaut.domain.Person
import io.github.joxebus.micronaut.domain.Role
import io.github.joxebus.micronaut.domain.User
import io.github.joxebus.micronaut.service.PersonService
import io.github.joxebus.micronaut.service.RoleService
import io.github.joxebus.micronaut.service.UserService
import io.micronaut.context.annotation.Requires
import io.micronaut.context.env.Environment
import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.runtime.server.event.ServerStartupEvent

import javax.inject.Singleton

@Slf4j
@CompileStatic
@Singleton
@Requires(notEnv = Environment.TEST)
class Bootstrap implements ApplicationEventListener<ServerStartupEvent> {

    final PersonService personService
    final RoleService roleService
    final UserService userService

    Bootstrap(PersonService personService, RoleService roleService, UserService userService) {
        this.personService = personService
        this.roleService = roleService
        this.userService = userService
    }

    @Override
    void onApplicationEvent(ServerStartupEvent event) {
        log.debug("Loading data...")
        loadUsersAndRoles()
        loadPeople()
        log.debug("... Finish loading data.")

    }

    void loadUsersAndRoles() {
        Role roleAdmin = new Role(name: "ADMIN")
        Role roleUser = new Role(name: "USER")
        if(!roleService.count()) {
            log.debug("Loading roles")
            roleService.save(roleAdmin)
            roleService.save(roleUser)
        }

        if(!userService.count()) {
            log.debug("Loading admin user")
            User userAdmin = new User()
            userAdmin.with {
                username = "admin@admin.com"
                password = "admin123"
                roles = [roleAdmin.name, roleUser.name] as Set
            }
            userService.save(userAdmin)
        }
    }

    void loadPeople() {
        if(!personService.count()){
            log.debug("Loading people")
            personService.save(new Person(name: "Omar", lastName: "Bautista", age: 31, phone:"444-124-2253"))
            personService.save(new Person(name: "Juan", lastName: "Contreras", age: 29, phone:"333-234-8763"))
            personService.save(new Person(name: "Diego", lastName: "Luna", age: 30, phone:"553-763-6353"))
        }
    }
}
