package io.pick5.registration.router;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegistrationRouter {
	
	
	@Value("${routes.register}")
	private static String REGISTER_ROUTE;
	
//
//	
//	  @Bean
//	    public RouterFunction<ServerResponse> customerRouterFunction(RegistrationHandler contactPersonHandler) {
//
//	        return route(POST("/api/v2/contact").and(accept(MediaType.APPLICATION_JSON_UTF8)), contactPersonHandler::getAllContactPersons);
//
//	    }
}
