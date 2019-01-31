package io.pick5.email.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import io.pick5.email.handler.EmailHandler;

@Configuration
public class EmailRouter {
	
	@Value("${path.email}")
	private String emailPath;
	
	 @Bean
	 public RouterFunction<ServerResponse> emailRouterFunction(EmailHandler emailHandler) {
	     return route(POST(emailPath).and(accept(MediaType.APPLICATION_JSON_UTF8)), 
	    	emailHandler::sendConfirmationEmail);
	 }
}
