package io.pick5.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.pick5.auth.domain.RedisAuthentication;
import io.pick5.auth.domain.TokenAuth;
import io.pick5.auth.handler.AuthenticationRedisReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class PasswordlessAuthenticator {

  @Autowired
  AuthenticationRedisReactiveRepository authenticationRedisReactiveRepository;

  public Mono<Boolean> authenticate(TokenAuth tokenAuth) {
    try {
      return authenticationRedisReactiveRepository.findOneByUsername(tokenAuth.getUsername())
          .flatMap(principal -> {
            if (principal != null) {
              boolean isValidName = tokenAuth.getUsername().equals(principal.getUsername()),
               isValidToken = tokenAuth.getToken().equals(principal.getToken()),
               hasExpired = !principal.hasExpired();
              return Mono.just(isValidName && isValidToken && hasExpired);
            }
            return Mono.just(false);
          });
    } catch (Exception ex) {
      log.error("Exception Encountered!");
      return Mono.just(false);
    }
  }

  public Mono<Boolean> authenticate(String username, String token) {
    try {
      return authenticationRedisReactiveRepository.findOneByUsername(username)
        .flatMap(principal -> {
          if (principal != null) {
            boolean isValidName = username.equals(principal.getUsername()),
              isValidToken = token.equals(principal.getToken()),
              hasExpired = !principal.hasExpired();
            return Mono.just(isValidName && isValidToken && hasExpired);
          }
          return Mono.just(false);
        });
    } catch (Exception ex) {
      log.error("Exception Encountered!");
      return Mono.just(false);
   }
  }

  public String generateToken(String username) {
    String token = "11111";
    try {
      authenticationRedisReactiveRepository.save(new RedisAuthentication(username, token)).log().subscribe();
    } catch (Exception ex) {
      log.error("Exception encountered generating token: " + ex);
    }
    return token;
  }

}