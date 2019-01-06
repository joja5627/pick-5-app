package io.pick5.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import io.pick5.domain.EnableDomainModule;
import io.pick5.exceptions.EnableExceptionsModule;
import io.pick5.module.asyncHttpClient.EnableAsyncHttpClientModule;

@EnableExceptionsModule
@EnableDomainModule
@EnableAsyncHttpClientModule
@SpringBootApplication
@EnableEurekaClient
public class EmailApplication  {
    public static void main(String[] args) {
    	
        SpringApplication.run(EmailApplication.class, args);
        
    }

}
