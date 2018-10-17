package com.nearsoft.micronaut.domain

import grails.gorm.annotation.Entity

@Entity
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
