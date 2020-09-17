package io.github.joxebus.micronaut.domain

import grails.gorm.annotation.Entity
import groovy.transform.Canonical

@Entity
@Canonical
class User {

    String username
    String password

    Set<String> roles = []

    static mapping = {
        username email: true, unique: true
        password nullable: false
    }

    void addRole(String role) {
        roles.add(role)
    }

    void removeRole(String role) {
        roles.remove(role)
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
