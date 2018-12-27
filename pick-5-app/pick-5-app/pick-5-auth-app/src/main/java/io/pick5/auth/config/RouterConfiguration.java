package io.pick5.auth.config;


import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import io.pick5.auth.CONSTANTS;
import io.pick5.auth.handler.FunctionalRouterWebHandler;



@Configuration
public class RouterConfiguration {

  @Bean 	 	
  public FunctionalRouterWebHandler handler() {
    return new FunctionalRouterWebHandler();
  }

  @Bean
  @Autowired
  public RouterFunction<ServerResponse> routes(FunctionalRouterWebHandler handler) {
    return route(POST(CONSTANTS.API_ROUTER_USER_ONE).and(accept(APPLICATION_JSON)), handler::getOneUser)
        .andRoute(POST(CONSTANTS.API_ROUTER_USER_NEW).and(accept(APPLICATION_JSON)), handler::saveOneUser)
        .andRoute(DELETE(CONSTANTS.API_ROUTER_USER_ONE).and(accept(APPLICATION_JSON)), handler::deleteOneUser)
        .andRoute(PUT(CONSTANTS.API_ROUTER_USER_ONE).and(accept(APPLICATION_JSON)), handler::updateOneUser)
        .andRoute(GET(CONSTANTS.API_ROUTER_USER_ALL).and(accept(APPLICATION_JSON)), handler::getAllUsers);
  }

}