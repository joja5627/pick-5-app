package io.pick5.registration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import io.pick5.domain.User;
import io.pick5.module.asyncHttpClient.AsyncHttpClient;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmailServiceProxyRetryable implements EmailService {

	@Value("${routes.base.email.app}")
	private static String EMAIL_APP_PATH;
	
	@Autowired
	AsyncHttpClient asyncHttpClient;
	
	
	public Mono<User> sendConfirmationEmail(Mono<User> newUser){
		return asyncHttpClient
				.asyncRequest(EMAIL_APP_PATH, User.class, HttpMethod.POST).entity();
		
	}
}
