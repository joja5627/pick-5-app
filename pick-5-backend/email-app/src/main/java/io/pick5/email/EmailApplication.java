package io.pick5.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.pick5.domain.EnableDomainModule;
import io.pick5.exceptions.EnableExceptionsModule;
import io.pick5.module.asyncHttpClient.EnableAsyncHttpClientModule;



@EnableExceptionsModule
@EnableDomainModule
@EnableAsyncHttpClientModule
@SpringBootApplication
public class EmailApplication  {
    public static void main(String[] args) {
    	
        SpringApplication.run(EmailApplication.class, args);
        
    }

}
