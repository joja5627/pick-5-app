package io.pick5.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;

import io.pick5.auth.router.MainRouter;
import io.pick5.handlers.ErrorHandler;

@Configuration
@EnableWebFlux
public class WebFluxConfig {
//	 @Bean
//	    ApiHandler apiHandler(final GeoLocationService geoLocationService, final SunriseSunsetService sunriseSunsetService,
//	                          final ErrorHandler errorHandler) {
//	        return new ApiHandler(geoLocationService, sunriseSunsetService, errorHandler);
//	    }
	
//	    @Bean
//	    GeoLocationService locationService(@Value("${GeoLocationServiceImpl.endPoint}") final String endPoint) {
//	        return new GeoLocationServiceImpl(endPoint);
//	    }
	
//	    @Bean
//	    SunriseSunsetService sunriseSunsetService(@Value("${SunriseSunsetServiceImpl.endPoint}") final String endPoint) {
//	        return new SunriseSunsetServiceImpl(endPoint);
//	    }
	
	    @Bean
	    ErrorHandler errorHandler() {
	        return new ErrorHandler();
	    }
	
	    @Bean
	    RouterFunction<?> mainRouterFunction( final ErrorHandler errorHandler) {
	        return MainRouter.doRoute(errorHandler);
	    }
}

