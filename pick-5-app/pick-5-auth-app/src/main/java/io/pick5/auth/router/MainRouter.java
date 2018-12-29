package io.pick5.auth.router;


import org.springframework.web.reactive.function.server.RouterFunction;

public class MainRouter {
//final ErrorHandler errorHandler
    public static RouterFunction<?> doRoute() {
        return StaticRouter.doRoute();
            
    }
}
