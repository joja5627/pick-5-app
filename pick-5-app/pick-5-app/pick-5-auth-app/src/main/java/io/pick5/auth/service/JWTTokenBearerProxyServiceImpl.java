package io.pick5.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.pick5.module.asyncHttpClient.AsyncHttpClient;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class JWTTokenBearerProxyServiceImpl {
	
	
	private final AsyncHttpClient asyncHttpClient;
	
	@Autowired
	public JWTTokenBearerProxyServiceImpl(AsyncHttpClient asyncHttpClient) {
		this.asyncHttpClient = asyncHttpClient;
	}
	
	public  Mono<Authentication> getAuthorizationToken(){
		return null;
	}
	
	


}
