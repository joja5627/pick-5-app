package io.pick5.auth.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.logout.ServerLogoutHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class LogoutHandler implements ServerLogoutHandler {

	@Override
	public Mono<Void> logout(WebFilterExchange exchange, Authentication authentication) {
		
		log.info("logout!");
		
		return Mono.empty();
	}

}
