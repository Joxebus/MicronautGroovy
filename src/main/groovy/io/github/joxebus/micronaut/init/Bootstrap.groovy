package io.github.joxebus.micronaut.init


import groovy.util.logging.Slf4j
import io.github.joxebus.micronaut.domain.Person
import io.github.joxebus.micronaut.domain.Role
import io.github.joxebus.micronaut.domain.User
import io.github.joxebus.micronaut.enums.RoleEnum
import io.github.joxebus.micronaut.service.PersonService
import io.github.joxebus.micronaut.service.RoleService
import io.github.joxebus.micronaut.service.UserService
import io.micronaut.context.annotation.Requires
import io.micronaut.context.env.Environment
import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.runtime.server.event.ServerStartupEvent

import javax.inject.Singleton

@Slf4j
@Singleton
@Requires(notEnv = Environment.TEST)
class Bootstrap implements ApplicationEventListener<ServerStartupEvent> {

    final RoleService roleService
    final PersonService personService
    final UserService userService

    Bootstrap(RoleService roleService, PersonService personService, UserService userService) {
        this.roleService = roleService
        this.personService = personService
        this.userService = userService
    }

    @Override
    void onApplicationEvent(ServerStartupEvent event) {
        log.debug("Loading data...")
        loadAdminUser()
        loadPeople()
        log.debug("... Finish loading data.")

    }

    void loadAdminUser() {

        if(!userService.count()) {
            log.debug("Loading admin user")
            Role admin = new Role(name: RoleEnum.ADMIN.toString())
            Role user = new Role(name: RoleEnum.USER.toString())

            roleService.with {
                save(admin)
                save(user)
            }

            User userAdmin = new User()
            userAdmin.with {
                username = "admin@admin.com"
                password = "admin123"
                addToRoles(admin)
                addToRoles(user)
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
