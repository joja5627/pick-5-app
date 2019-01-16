package io.pick5.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

import io.pick5.domain.EnableDomainModule;
import io.pick5.exceptions.EnableExceptionsModule;
import io.pick5.handlers.EnableHandlersModule;


@EnableWebFlux
@EnableExceptionsModule
@EnableDomainModule
@EnableHandlersModule
@SpringBootApplication
@EnableReactiveMongoRepositories
public class UserApplication {
	
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
