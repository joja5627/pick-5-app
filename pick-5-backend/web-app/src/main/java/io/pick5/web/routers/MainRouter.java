package io.pick5.web.routers;

import org.springframework.web.reactive.function.server.RouterFunction;

import io.pick5.web.service.handlers.ApiHandler;
import io.pick5.web.service.handlers.ErrorHandler;

public class MainRouter {

	public static RouterFunction<?> doRoute(final ApiHandler handler, final ErrorHandler errorHandler) {
		return ApiRouter.doRoute(handler, errorHandler).andOther(StaticRouter.doRoute());
	}
}
