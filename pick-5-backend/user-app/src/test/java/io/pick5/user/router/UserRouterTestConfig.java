package io.pick5.user.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.pick5.handlers.ErrorHandler;

@Configuration
public class UserRouterTestConfig {
	@Bean
	public ErrorHandler errorHandler() {
		return new ErrorHandler();
	}
}
