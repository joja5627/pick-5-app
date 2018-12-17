package io.pick5.account.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfiguration {
//
//	@Bean
//	public FunctionalRouterWebHandler handler() {
//		return new FunctionalRouterWebHandler();
//	}
//
//	@Bean
//	@Autowired
//	public RouterFunction<ServerResponse> routes(FunctionalRouterWebHandler handler) {
//		return route(POST(API_ROUTER_USER_ONE).and(accept(APPLICATION_JSON)), handler::getOneUser)
//				.andRoute(POST(API_ROUTER_USER_NEW).and(accept(APPLICATION_JSON)), handler::saveOneUser)
//				.andRoute(DELETE(API_ROUTER_USER_ONE).and(accept(APPLICATION_JSON)), handler::deleteOneUser)
//				.andRoute(PUT(API_ROUTER_USER_ONE).and(accept(APPLICATION_JSON)), handler::updateOneUser)
//				.andRoute(GET(API_ROUTER_USER_ALL).and(accept(APPLICATION_JSON)), handler::getAllUsers);
//	}

}