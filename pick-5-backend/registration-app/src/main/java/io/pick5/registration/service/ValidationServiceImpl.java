//package io.pick5.registration.service;
//
//import org.springframework.stereotype.Service;
//
//import io.pick5.domain.User;
//import io.pick5.exceptions.ValidationException;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//@Service
//public class ValidationServiceImpl implements ValidationService {
//	
//	
//	public Mono<User> validateUser(User newUser,Flux<User> currentUsers ) {
//		
//		return checkExistingEmail(newUser, currentUsers)
//				.switchIfEmpty(checkExistingUser(newUser, currentUsers));
//			
//	}
//	
//	private Mono<User> checkIfTrue(Mono<Boolean> alreadyExists, Mono<User> newUser) {
//		
//		return alreadyExists.flatMap(exists -> {
//			
//			if(exists) {
//				
//				return Mono.error(new ValidationException());
//			}
//			
//			return newUser;
//		});
//	}
//	
//		
//	private Mono<User> checkExistingEmail(User newUser,Flux<User> currentUsers){
//		
//			return checkIfTrue(currentUsers.any(currentUser -> 
//					newUser.getEmail().equals(currentUser.getEmail())),newUser);
//		
//	}
//	
//
//	private  Mono<User> checkExistingUser(Mono<User> newUser,Flux<User> currentUsers){
//		return newUser.flatMap(user -> {
//			return checkIfTrue(currentUsers.any(currentUser -> 
//				user.getEmail().equals(currentUser.getEmail())),newUser);
//		});	
//	}
//
//}
