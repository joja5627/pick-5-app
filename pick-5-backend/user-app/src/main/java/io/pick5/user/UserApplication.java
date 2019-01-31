package io.pick5.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.pick5.domain.EnableDomainModule;
import io.pick5.exceptions.EnableExceptionsModule;
import io.pick5.handlers.EnableHandlersModule;



@EnableExceptionsModule
@EnableDomainModule
@EnableHandlersModule
@SpringBootApplication
public class UserApplication {
	
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
  
}
