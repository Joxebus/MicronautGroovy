package com.nearsoft.micronaut

import com.nearsoft.micronaut.api.PersonApi
import com.nearsoft.micronaut.domain.Person
import com.nearsoft.micronaut.service.PersonService
import grails.gorm.transactions.Transactional
import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpStatus
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class PersonControllerSpec extends Specification {

    @Shared @AutoCleanup EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer)
    @Shared PersonApi client
    @Shared PersonService service

    void setupSpec(){
        client = embeddedServer.applicationContext.getBean(PersonApi)
        service = embeddedServer.applicationContext.getBean(PersonService)
    }


    @Transactional
    void cleanup(){
        Person.list()*.delete()
    }

    def "/people should return 2 elements" (){
        given:
        service.save(new Person(name: "Mia", lastName: "Bautista", age: 2, phone: "235-547-8761" ))
        service.save(new Person(name: "Sofia", lastName: "Ojeda", age: 10, phone: "765-234-8623"))
        when:
        List<Person> people = client.list()
        then:
        people.size() == 2
    }

    @Unroll("Creating person with name: #name ")
    def "/people should save via post"(){
        when:
        Person person = new Person(name:name, lastName:lastName, age:age, phone: phone)

        def response = client.create(person)

        then:
        response.with {
            it.name     == name
            it.lastName == lastName
            it.age      == age
            it.phone    == phone
        }
        where:
        name        | lastName      |   age     | phone
        "Krista"    | "Bautista"    |   3       | "987-234-6324"
        "Maria"     | "Ojeda"       |   29      | "873-513-6249"
        "Jorge"     | "Valenuela"   |   31      | "753-263-1832"
    }

    def "/people show specific person"(){
        given:
        Person person = client.create(new Person([name:"Mia", lastName:  "Bautista",age:  2,phone: "235-547-8761"]))
        when:
        def response = client.show(person.id)
        then:
        response.with {
            name     == person.name
            lastName == person.lastName
            age      == person.age
            phone    == person.phone
        }
    }


    def "/people delete"(){
        given:
        service.save(new Person(name: "Mia", lastName: "Bautista", age: 2, phone: "235-547-8761" ))
        Long id = service.save(new Person(name: "Sofia", lastName: "Ojeda", age: 10, phone: "765-234-8623")).id

        when:
        def entity = client.delete(id)

        then:
        entity.status == HttpStatus.OK

    }

    def "/people update the first element"(){
        given:
        Person person = client.create(new Person([name:"Mia", lastName:  "Bautista",age:  2,phone: "235-547-8761"]))

        when:
        def response = client.update(new Person(id:person.id, name:"Josue", lastName: "Fernandez", age: 39, phone: '552-876-2341'))

        then:
        response.with {
            id        == 1
            name      == "Josue"
            lastName  == "Fernandez"
            age       == 39
            phone     == '552-876-2341'
        }

        person.with {
            id       == response.id
            name     != response.name
            lastName != response.lastName
            age      != response.age
            phone    != response.phone
        }
    }


}
