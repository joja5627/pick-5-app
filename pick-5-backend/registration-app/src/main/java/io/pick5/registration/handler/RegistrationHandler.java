package io.pick5.registration.handler;

import static org.springframework.web.reactive.function.BodyExtractors.toMono;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import io.pick5.domain.User;
import io.pick5.handlers.ErrorHandler;
import io.pick5.registration.service.EmailServiceProxyRetryable;
import io.pick5.registration.service.UserServiceProxyRetryablImpl;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RegistrationHandler {
	
	 @Autowired
	 private final ErrorHandler errorHandler;
	
//	 @Autowired
//	 private final RegistrationRepository registrationRepository;
	 
	 @Autowired
	 private final UserServiceProxyRetryablImpl userServiceProxyRetryableImpl;
	 
	 @Autowired
	 private final EmailServiceProxyRetryable emailServiceProxyRetryableImpl;
	 
	 Mono<ServerResponse> okResponse(Mono<User> userResponseMono) {
		    return userResponseMono.flatMap(userResponse ->
		        ServerResponse.ok().build());
	 }
	 
	 Mono<ServerResponse> errorResponse(Throwable error) {
		    return ServerResponse
		    				.status(HttpStatus.INTERNAL_SERVER_ERROR)
		    					.body(Mono.error(error.getCause()), String.class);
	 }
	 
	 private Mono<User> addConfirmationCode(Mono<User> monoUser){
		 return monoUser.flatMap(user -> {
			 user.setConfirmationCode(UUID.randomUUID().toString());
			 return Mono.just(user);
		 });
	 }
	 
	 public Mono<ServerResponse> registerUser(ServerRequest serverRequest) {
	    	
	        Mono<User> newUser = serverRequest.body(toMono(User.class));
	        
	        return newUser
		        	.transform(this::addConfirmationCode)
		        		.transform(emailServiceProxyRetryableImpl::sendConfirmationEmail)
		        			.log("userServiceProxyRetryableImpl::sendConfirmationEmail::")
		        				.transform(this::okResponse)
		        					.onErrorResume(this::errorResponse);
//	        		.transform(userServiceProxyRetryableImpl::saveNewUser)
//	        			.log("userServiceProxyRetryableImpl::saveNewUser::OK")
	        			
	        								
	    }
}
