package io.pick5.registration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.EntityResponse;

import io.pick5.domain.User;
import io.pick5.module.asyncHttpClient.AsyncHttpClient;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceProxyRetryablImpl implements UserService {

	
	@Value("${routes.base.user.app}")
	private static String USER_APP_PATH;
	
	@Autowired
	AsyncHttpClient asyncHttpClient;
		
	
	public EntityResponse<Flux<User>> getCurrentUsers(){
		return asyncHttpClient
					.asyncRequestStream(USER_APP_PATH, User.class, HttpMethod.GET);
	}
	
	public EntityResponse<Mono<User>> updateUserConfirmationCode(final Mono<User> newUser){

		return asyncHttpClient
			.asyncRequest(USER_APP_PATH, User.class, HttpMethod.POST,newUser);

	}
	
}
