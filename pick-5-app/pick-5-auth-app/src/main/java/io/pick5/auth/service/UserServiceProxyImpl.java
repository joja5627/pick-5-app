package io.pick5.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.pick5.domain.user.UserEntity;
import io.pick5.module.asyncHttpClient.AsyncHttpClient;

@Service
public class UserServiceProxyImpl {
	
	@Autowired
	AsyncHttpClient asyncHttpClient;
	
	UserServiceProxyImpl(AsyncHttpClient asyncHttpClient){
		this.asyncHttpClient = asyncHttpClient;
	}
	
	public UserEntity saveUserEntity(String userName, String email) {
		
		return null;
	}
	

}
