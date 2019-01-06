package io.pick5.user.router;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;

import io.pick5.handlers.ErrorHandler;
import io.pick5.registration.handler.RegistrationHandler;

public class RegistrationRouter {
	
	@Value("${routes.base.version}")
	private static String BASE_VERSION;
	
	@Value("${routes.register}")
	private static String REGISTER_ROUTE;

	public static RouterFunction<?> doRoute(final RegistrationHandler handler, final ErrorHandler errorHandler) {
		
        return nest(path(BASE_VERSION),
        	        route(POST(REGISTER_ROUTE)
        	        		.and(accept(APPLICATION_JSON_UTF8)),handler::register)
        				.andOther(route(RequestPredicates.all(),errorHandler::notFound)));
    }

}
