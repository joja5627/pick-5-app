package io.pick5.registration.router;



import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import io.pick5.registration.handler.RegistrationHandler;

@Configuration
public class RegistrationRouter {
	
	
	@Value("${routes.register}")
	private  String REGISTER_ROUTE;
	
	@Bean
	public RouterFunction<ServerResponse> customerRouterFunction(RegistrationHandler registrationHandler) {
		return route(POST(REGISTER_ROUTE)
				.and(accept(MediaType.APPLICATION_JSON_UTF8)), registrationHandler::registerUser);
	}
}
