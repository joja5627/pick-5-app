package io.pick5.web.routers;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import io.pick5.web.service.handlers.ApiHandler;
import io.pick5.web.service.handlers.ErrorHandler;

class ApiRouter {
	private static final String LINES_API_PATH = "/api/v1/lines";

	static RouterFunction<?> doRoute(final ApiHandler apiHandler, final ErrorHandler errorHandler) {

		return RouterFunctions.route(GET(LINES_API_PATH).and(accept(APPLICATION_JSON)), apiHandler::getBovadaLines)
				.andOther(route(RequestPredicates.all(), errorHandler::notFound));

	}
}
