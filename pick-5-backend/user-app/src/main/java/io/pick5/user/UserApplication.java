package io.pick5.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import io.pick5.domain.EnableDomainModule;
import io.pick5.exceptions.EnableExceptionsModule;

@EnableExceptionsModule
@EnableDomainModule
@SpringBootApplication
@EnableEurekaClient
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
