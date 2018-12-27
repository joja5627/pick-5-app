package io.pick5.auth.router;


import org.springframework.web.reactive.function.server.RouterFunction;

import io.pick5.handlers.ErrorHandler;

public class MainRouter {

    public static RouterFunction<?> doRoute(final ErrorHandler errorHandler) {
        return StaticRouter.doRoute();
            
    }
}
