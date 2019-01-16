package io.pick5.registration.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.pick5.domain.User;
import io.pick5.handlers.ErrorHandler;
import io.pick5.registration.service.EmailServiceProxyRetryable;
import io.pick5.registration.service.UserServiceProxyRetryablImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RegistrationController {
	@Autowired
	UserServiceProxyRetryablImpl userServiceImpl;
	@Autowired
	EmailServiceProxyRetryable emailServiceImpl;
	
	@Autowired
	ErrorHandler errorHandler;
	
//	@Autowired
//	ValidationServiceImpl validationService;
	

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody User newUser) {
    	

		Flux<User> users = userServiceImpl.getCurrentUsers();
				
		Mono<User> validUser = Mono.just(newUser);
		
		log.info("Save User");
		log.info("Send Confirmation Email");
		
		
		Mono.fromCallable(() -> userServiceImpl
				.updateUserConfirmationCode(validUser))
					.onErrorResume(throwable -> Mono.error(throwable));
		
		return ResponseEntity.ok().build();
		
	
//		
//		
//		Mono<User> validUser = validationService
//					.validateUser(newUser,users.entity())
//						.onErrorResume(throwable -> Mono.error(throwable));		
		
		

		
//		Mono.fromCallable(() -> emailServiceImpl
//				.sendConfirmationEmail(validUser))
//					.onErrorResume(throwable -> Mono.error(throwable));

    }
}
