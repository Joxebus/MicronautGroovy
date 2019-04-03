package com.nearsoft.micronaut

import com.nearsoft.micronaut.domain.Person
import com.nearsoft.micronaut.service.PersonService
import grails.gorm.transactions.Rollback
import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

@Rollback
class PersonServiceSpec extends Specification {

    @Shared @AutoCleanup EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer)
    @Shared PersonService service = embeddedServer.applicationContext.getBean(PersonService)

    def "Service can save, count and find all"() {
        expect:
        service.count() == 0

        when:
        service.save(new Person(name: "Arturo", lastName: "Vargas", age: 26, phone:"653-762-8614"))

        then:
        service.count() == 1

        when:
        List<Person> people = service.findAll()

        then:
        people.size() == 1
    }

    @Unroll("Testing for name #name #lastName")
    def "Service can save and show a person by id"(){
        given:
        Long id = service.save(new Person(name:name, lastName: lastName, age:age, phone: phone)).id

        when:
        Person person = service.find(id)

        then:

        person.with {
            it.id == id
            it.name == name
            it.lastName == lastName
            it.age == age
            it.phone == phone
        }

        where:

        name        | lastName      | age    | phone
        "Krista"    | "Bautista"    | 2      | "98324786234"
        "Jorge"     | "Reyes"       | 31     | "65234"
        "Ramon"     | "Ayala"       | 27     | "234235"
    }

    def "Service can update a person by id"(){
        given:
        Long id = service.save(new Person(name:"Jorge", lastName: "Bautista", age:31, phone: "653-425-6482")).id

        when:
        Person person = service.find(id)

        and:
        person.with {
            name = "Omar"
            phone = "752-863-1434"
        }

        person = service.save(person)

        then:

        person.with {
            it.id == id
            name == "Omar"
            lastName == "Bautista"
            age == 3
            phone == "752-863-1434"
        }
    }
}
