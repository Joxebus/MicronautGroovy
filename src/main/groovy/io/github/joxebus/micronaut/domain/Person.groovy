package io.github.joxebus.micronaut.domain

import grails.gorm.annotation.Entity
import groovy.transform.Canonical

@Entity
@Canonical
class Person {

    String name
    String lastName
    int age
    String phone

    static constraints = {
        lastName nullable: true
        phone nullable: true
    }

}
