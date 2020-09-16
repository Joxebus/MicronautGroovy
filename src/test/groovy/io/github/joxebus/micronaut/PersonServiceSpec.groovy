package io.github.joxebus.micronaut

import io.github.joxebus.micronaut.domain.Person
import io.github.joxebus.micronaut.service.PersonService
import grails.gorm.transactions.Rollback
import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import org.grails.datastore.mapping.validation.ValidationException
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

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

    def "Person should be with an age between 18 and 99"() {
        given:
        Person person = new Person()
        person.with {
            name = "Omar"
            lastName = "Bautista"
            age = 12
        }

        when:
        service.save(person)

        then:
        thrown(ValidationException)
    }

    def "Service can save and show a person by id"(){
        given:
        Long id = service.save(new Person(name:"Krista", lastName: "Bautista", age:22, phone: "654-763-8763")).id

        when:
        Person person = service.find(id)

        then:

        person.with {
            it.id == id
            name == "Krista"
            lastName == "Bautista"
            age == 3
            phone == "654-763-8763"
        }
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
