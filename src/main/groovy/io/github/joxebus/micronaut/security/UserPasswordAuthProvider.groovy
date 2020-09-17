package io.github.joxebus.micronaut.security


import io.github.joxebus.micronaut.domain.User
import io.github.joxebus.micronaut.service.UserService
import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationException;
import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.UserDetails
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher

import javax.inject.Singleton
import javax.inject.Inject;

@Singleton
class UserPasswordAuthProvider implements AuthenticationProvider {

    @Inject
    UserService userService

    @Override
    Publisher<AuthenticationResponse> authenticate(@Nullable HttpRequest<?> httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
        User user = new User(username:authenticationRequest.identity, password: authenticationRequest.secret )
        User dbUser = userService.findByUsername(user.username)
        Flowable.create(emitter -> {
            if (dbUser?.password == user.encryptPassword()) {
                emitter.onNext(new UserDetails(user.username, user.roles))
                emitter.onComplete()
            } else {
                emitter.onError(new AuthenticationException(new AuthenticationFailed()))
            }
        }, BackpressureStrategy.ERROR)
    }
}
