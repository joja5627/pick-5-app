package io.pick5.auth.config;

import java.util.function.Function;

//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**
 * This converter extracts a bearer token from a WebExchange and
 * returns an Authentication object if the JWT token is valid.
 * Validity means is well formed and signature is correct
 */
public class ServerHttpBearerAuthenticationConverter implements Function<ServerWebExchange, Mono<Authentication>> {


    @Override
    public Mono<Authentication> apply(ServerWebExchange serverWebExchange) {
    	return Mono.just(null);
//        return Mono.justOrEmpty(serverWebExchange)
//                	.flatMap(AuthorizationHeaderPayload::extract)
//                	.filter(matchBearerLength)
//                	.flatMap(isolateBearerValue)
//                	.zipWhen(this::sunriseSunset, LocationResponse::new).log();
//                	.flatMap(Mono.just( UsernamePasswordAuthenticationToken("subject", List.of("ADMIN")))).log();
               
//                .flatMap(jwtVerifier::check)
//                .flatMap(UsernamePasswordAuthenticationBearer::create).log();
    }
}
