package io.pick5.wed.util;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import io.pick5.web.tags.UnitTest;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;
@UnitTest
public class WebFluxRouterTest {
	
	public RouterFunction<?> userEndpoints() {
//		RouterFunctions.route(GET(LINES_API_PATH).and(accept(APPLICATION_JSON)), apiHandler::getBovadaLines)
//		.andOther(route(RequestPredicates.all(), errorHandler::notFound));
		return RouterFunctions
				.route(GET("/"), request -> ok()
						.body(Mono.just("Hello"), String.class));
		
	}

	@Test
	public void routerTest() {
		
		
	
		
		
		
		
	}

}
