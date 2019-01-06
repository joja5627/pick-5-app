package io.pick5.registration.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.EntityResponse;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import io.pick5.domain.User;
import io.pick5.handlers.ErrorHandler;
import io.pick5.registration.service.EmailServiceProxyRetryable;
import io.pick5.registration.service.UserServiceProxyRetryablImpl;
import io.pick5.registration.service.ValidationServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RegistrationHandler {
	
	@Autowired
	UserServiceProxyRetryablImpl userServiceImpl;
	@Autowired
	EmailServiceProxyRetryable emailServiceImpl;
	
	@Autowired
	ErrorHandler errorHandler;
	
	@Autowired
	ValidationServiceImpl validationService;
	
	public Mono<ServerResponse> register(ServerRequest request){
		
		log.info(request.toString());
		
		EntityResponse<Flux<User>> users = userServiceImpl
												.getCurrentUsers();
		
		log.info(users.toString());
		
		Mono<User> newUser = 
						request.bodyToMono(User.class);
		
		
		Mono<User> validUser = validationService
					.validateUser(newUser,users.entity())
						.onErrorResume(throwable -> Mono.error(throwable));		
		
			
		Mono.fromCallable(() -> userServiceImpl
				.updateUserConfirmationCode(validUser))
					.onErrorResume(throwable -> Mono.error(throwable));
		
		Mono.fromCallable(() -> emailServiceImpl
				.sendConfirmationEmail(validUser))
					.onErrorResume(throwable -> Mono.error(throwable));

		
		return serverResponse(validUser);
		
		
	}
	
	
	Mono<User> extractEntity(final EntityResponse<Mono<User>> response){
		
		return (Mono<User>) response.entity();
	}

	Mono<ServerResponse> serverResponse(Mono<User> user) {
		
		return user.flatMap(userRes ->{
	        return ServerResponse.ok().body(Mono.just(userRes), User.class);

		});
	  }    

}
