package io.github.joxebus.micronaut.security

import groovy.util.logging.Slf4j
import io.github.joxebus.micronaut.domain.Role
import io.github.joxebus.micronaut.domain.User
import io.github.joxebus.micronaut.enums.RoleEnum
import io.github.joxebus.micronaut.service.RoleService
import io.github.joxebus.micronaut.service.UserService
import io.micronaut.security.oauth2.client.OpenIdProviderMetadata
import io.micronaut.security.oauth2.configuration.OauthClientConfiguration
import io.micronaut.security.oauth2.endpoint.token.response.OpenIdClaims
import io.micronaut.security.oauth2.endpoint.token.response.validation.OpenIdClaimsValidator

import javax.inject.Inject
import javax.inject.Singleton

@Slf4j
@Singleton
class OpenIdUserValidator implements OpenIdClaimsValidator {

    @Inject
    UserService userService

    @Inject
    RoleService roleService

    @Override
    boolean validate(OpenIdClaims claims, OauthClientConfiguration clientConfiguration, OpenIdProviderMetadata providerMetadata) {
        if( claims?.email ) {
            verifyAndRegister(claims.email)
            return true
        }
        return false
    }

    void verifyAndRegister(String email) {
        User user = userService.findByUsername(email)
        if(!user) {
            log.debug("Registering new user: {}", email)
            Role userRole = roleService.findByName(RoleEnum.USER.toString())
            user = new User()
            user.with {
                username:email
                password:UUID.randomUUID().toString()
                addToRoles(userRole)
            }
            userService.save(user)
        } else {
            log.debug("User [{}] already registered", email)
        }
    }
}
