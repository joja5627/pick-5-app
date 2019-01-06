package io.pick5.email.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import io.pick5.domain.User;
import io.pick5.email.service.SmtpEmailServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmailHandler {
	
	@Autowired
	SmtpEmailServiceImpl emailService;
	
	public Mono<ServerResponse> sendConfirmationEmail(ServerRequest request){
		
		  Mono<User> userMono = request.bodyToMono(User.class);
	        
	       log.info(userMono.toString());
		
		return ServerResponse.ok().build();
	}

}
