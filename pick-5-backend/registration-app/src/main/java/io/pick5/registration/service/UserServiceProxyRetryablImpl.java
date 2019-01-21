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
public class UserServiceProxyRetryablImpl implements UserService {

	@Value("${app.path.user-save}")
	private String SAVE_USER_PATH;
	
	@Autowired
	AsyncHttpClient asyncHttpClient;
	
	public Mono<User> saveNewUser(final Mono<User> newUser){
		return asyncHttpClient
					.asyncRequest(SAVE_USER_PATH, User.class, HttpMethod.POST,newUser);
	}
}
