package io.pick5.user.router;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;

import io.pick5.handlers.ErrorHandler;
import io.pick5.user.handler.UserApiHandler;

public class UserRouter {
	
	@Value("${routes.base.version}")
	private static String BASE_VERSION;
	
	@Value("${routes.save}")
	private static String SAVE_ROUTE;
	
	@Value("${routes.get}")
	private static String GET_ROUTE;
	
	@Value("${routes.find_all}")
	private static String FIND_ALL_ROUTE;
	
	@Value("${routes.update}")
	private static String UPDATE_ROUTE;
	
	@Value("${routes.delete}")
	private static String DELETE_ROUTE;

	public static RouterFunction<?> doRoute(final UserApiHandler handler, final ErrorHandler errorHandler) {
		
        return nest(path(BASE_VERSION),
        		
        	        route(GET(FIND_ALL_ROUTE),handler::findAll)
        	        	.andRoute(POST(SAVE_ROUTE).and(accept(APPLICATION_JSON_UTF8)), handler::save)
        	        	.andRoute(PUT(UPDATE_ROUTE).and(accept(APPLICATION_JSON_UTF8)), handler::update)
        	        	.andRoute(DELETE(DELETE_ROUTE), handler::delete))
        				.andOther(route(RequestPredicates.all(), errorHandler::notFound));
    }

}
