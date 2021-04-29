package io.github.joxebus.micronaut.domain

import grails.gorm.annotation.Entity
import groovy.transform.Canonical

@Entity
@Canonical
class User {

    static hasMany = [roles:Role]

    String username
    String password

    static mapping = {
        table 'users'
        username email: true, unique: true
        password nullable: false
    }

    def beforeInsert() {
        encryptPassword()
    }

    String encryptPassword() {
        if(password) {
            password = password.sha256()
        }
        password
    }
}
