package io.github.joxebus.micronaut

import grails.gorm.transactions.Rollback
import io.github.joxebus.micronaut.domain.User
import io.github.joxebus.micronaut.service.UserService
import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import org.springframework.dao.DuplicateKeyException
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification


@Rollback
class UserServiceSpec extends Specification {

    @Shared @AutoCleanup EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer)
    @Shared UserService service = embeddedServer.applicationContext.getBean(UserService)

    def "Test userService encryptPassword and we can compare it"() {
        given:
        String myPassword = "mypassword123"
        User user = new User(username: "myuser@test.com", password: myPassword)

        when:
        User saved = service.save(user)
        user.encryptPassword()
        then:
        saved.password != myPassword
        saved.password == user.password
    }

    def "Cannot create 2 users with the same username"() {
        given:
        String myPassword = "mypassword123"
        User user1 = new User(username: "myuser@test.com", password: myPassword)
        User user2 = new User(username: "myuser@test.com", password: myPassword)

        when:
        service.save(user1)
        service.save(user2)

        then:
        thrown(DuplicateKeyException)
    }

    def "Test count of users"() {
        given:
        int count = service.count()
        User user = new User(username: "newuser@test.com", password: "something")

        when:
        service.save(user)

        then:
        old(service.count()) < service.count()
        count+1 == service.count()
    }
}
