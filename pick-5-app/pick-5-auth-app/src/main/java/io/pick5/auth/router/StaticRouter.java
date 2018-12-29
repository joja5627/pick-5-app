package io.pick5.auth.router;

import static org.springframework.web.reactive.function.server.RouterFunctions.resources;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.reactive.function.server.RouterFunction;

class StaticRouter {

    private static final String REGISTRATION_ROUTE = "/register";
    private static final String PUBLIC = "/register/register.html";

    static RouterFunction<?> doRoute() {
        return resources(REGISTRATION_ROUTE, new ClassPathResource(PUBLIC));
    }
}
