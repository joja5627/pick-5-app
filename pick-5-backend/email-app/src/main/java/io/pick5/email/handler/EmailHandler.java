package io.pick5.email.handler;

import static org.springframework.web.reactive.function.BodyExtractors.toMono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import io.pick5.domain.User;
import io.pick5.email.service.EmailServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class EmailHandler {
	
	@Autowired
	private final EmailServiceImpl emailService;
	
	 private Mono<ServerResponse> send(User user){
				try {
					emailService.buildAndSendEmail(user);
				}catch(Exception e) {
					log.error(e.getMessage(),e);
					return ServerResponse
		    				.status(HttpStatus.INTERNAL_SERVER_ERROR)
		    					.build();
				}
				
			 	log.info(String.format("Confirmation Email Sent for %s @ %s",user.getUsername(),user.getEmail()));

				return ServerResponse.ok().build();
			}
	 
	
	public Mono<ServerResponse> sendConfirmationEmail(ServerRequest request){
		return request
					.body(toMono(User.class))
						.flatMap(user -> send(user));
	}
	

}
