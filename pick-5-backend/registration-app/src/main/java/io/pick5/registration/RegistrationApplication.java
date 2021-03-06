package io.pick5.registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.pick5.domain.EnableDomainModule;
import io.pick5.exceptions.EnableExceptionsModule;
import io.pick5.handlers.EnableHandlersModule;
import io.pick5.module.asyncHttpClient.EnableAsyncHttpClientModule;

@EnableExceptionsModule
@EnableDomainModule
@EnableHandlersModule
@EnableAsyncHttpClientModule
@SpringBootApplication
public class RegistrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegistrationApplication.class, args);
    }
}
