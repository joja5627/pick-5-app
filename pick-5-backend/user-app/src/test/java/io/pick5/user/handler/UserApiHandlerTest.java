package io.pick5.user.handler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import io.pick5.tags.IntegrationTest;
import io.pick5.test.utils.HandlersHelper;
import io.pick5.user.domain.UserEntity;
import reactor.core.publisher.Mono;

@IntegrationTest
@DisplayName("UserApiHandlerTest Integration Tests")
public class UserApiHandlerTest {
	
	@Autowired
	private UserApiHandler userApiHandler;
	
	
	@BeforeEach
	void before() {
		
		   final ServerRequest
   						serverRequest = 
   								mock(ServerRequest.class);		
		    userApiHandler
				.findAll(serverRequest)
					.subscribe(this::clearMongo); 
	    
	}
	
	private void clearMongo(final ServerResponse serverResponse){
		
		 final ServerRequest
					serverRequest = 
							mock(ServerRequest.class);

		final List<UserEntity> entitiesResponse = 
        		(List<UserEntity>) HandlersHelper
        							.extractEntities(serverResponse, UserEntity.class);
		

		entitiesResponse.forEach(entity -> {
			
			when(serverRequest.pathVariable("id")).thenReturn(entity.getId());
			
			userApiHandler
				.delete(serverRequest)
					.subscribe(this::verifyServerResponseOk);

		});	
        
	}
	
	private final UserEntity createUserEntity(String id, String userName, String email) {
		
		UserEntity user = new UserEntity();
		
		user.setMongoId(id);
		user.setEmail(email);
		user.setUsername(userName);
		user.setActive(true);
		
		
		return user;
		
	}
	@SuppressWarnings("unchecked")
	private void verifyEntitiesResponse(final ServerResponse serverResponse, List<UserEntity> users) {

	        assertThat(serverResponse.statusCode(), is(HttpStatus.OK)); 
	      
			final List<UserEntity> entitiesResponse = 
	        		(List<UserEntity>) HandlersHelper.extractEntities(serverResponse, UserEntity.class);
	        
	        assertThat(entitiesResponse)
	        	.containsExactlyInAnyOrderElementsOf(users);
       
	   
	}
	
	

	 private void verifySingleServerResponse(final ServerResponse serverResponse, UserEntity entity) {

	        assertThat(serverResponse.statusCode(), is(HttpStatus.OK));
	        
	        final UserEntity entityResponse = 
	        		HandlersHelper.extractEntity(serverResponse, UserEntity.class);
	        
	        assertThat(entityResponse)
	        	.isEqualToComparingFieldByField(entity);
	   
	}
	 private void verifyServerResponseOk(final ServerResponse serverResponse) {

	        assertThat(serverResponse.statusCode(), is(HttpStatus.OK));
	   
	}
	
	@Test
    void saveTest() {	
		
        final ServerRequest
        		serverRequest = 
        			mock(ServerRequest.class);
        
        UserEntity user1 = 
        		createUserEntity(UUID.randomUUID().toString(),"username1","email1");
       
        when(serverRequest
    			.bodyToMono(UserEntity.class))
    						.thenReturn(Mono.just(user1));
        userApiHandler
    		.save(serverRequest)
    				.subscribe(res -> verifySingleServerResponse(res,user1));
        
    }
	@Test
    void findAllTest() {	
		
        final ServerRequest
        		createUserRequest = 
        			mock(ServerRequest.class);
        
        final ServerRequest
			findAllUserRequest = 
				mock(ServerRequest.class);
        
        String userId1 = UUID.randomUUID().toString();
        String userId2 = UUID.randomUUID().toString();
        
        UserEntity user1 = 
        		createUserEntity(userId1,"username1","email1");
        
        UserEntity user2 =  		
        		createUserEntity(userId2,"username2","email2");

        when(createUserRequest
    			.bodyToMono(UserEntity.class))
    						.thenReturn(Mono.just(user1));
        userApiHandler
			.save(createUserRequest)
				.subscribe(res -> verifySingleServerResponse(res,user1));
        
        when(createUserRequest
    			.bodyToMono(UserEntity.class))
    						.thenReturn(Mono.just(user2));
        
        userApiHandler
			.save(createUserRequest)
				.subscribe(res -> verifySingleServerResponse(res,user2));
	

		userApiHandler
			.findAll(findAllUserRequest)
				.subscribe(res -> 
					verifyEntitiesResponse(res,List.of(user1,user2)));
        
        
    }
	@Test
    void deleteTest() {	
		
        final ServerRequest
        		createUserRequest = 
        			mock(ServerRequest.class);
       
        
        final ServerRequest
			deleteUserRequest = 
				mock(ServerRequest.class);
        
        String userId1 = UUID.randomUUID().toString();
        String userId2 = UUID.randomUUID().toString();
        
        UserEntity user1 = 
        		createUserEntity(userId1,"username1","email1");
        
        UserEntity user2 =  		
        		createUserEntity(userId2,"username2","email2");

        when(createUserRequest
    			.bodyToMono(UserEntity.class))
    						.thenReturn(Mono.just(user1));
        userApiHandler
			.save(createUserRequest)
				.subscribe(res -> verifySingleServerResponse(res,user1));
        
        when(createUserRequest
    			.bodyToMono(UserEntity.class))
    						.thenReturn(Mono.just(user2));
        
        userApiHandler
			.save(createUserRequest)
				.subscribe(res -> verifySingleServerResponse(res,user2));
        
		when(deleteUserRequest.pathVariable("id"))
					.thenReturn(user1.getId());

        userApiHandler
     			.delete(deleteUserRequest)
     				.subscribe(res -> verifySingleServerResponse(res,user1));
   
    }
	@Test
    void updateTest() {	
		
        final ServerRequest
        		createUserRequest = 
        			mock(ServerRequest.class);
       
        
        final ServerRequest
				updateRequest = 
					mock(ServerRequest.class);
        
        String userId1 = UUID.randomUUID().toString();
        
        UserEntity user1 = 
        		createUserEntity(userId1,"username1","email1");

        when(createUserRequest
    			.bodyToMono(UserEntity.class))
    						.thenReturn(Mono.just(user1));
        userApiHandler
			.save(createUserRequest)
				.subscribe(res ->
					verifySingleServerResponse(res,user1));
        
        user1.setUsername("newUserName");

		when(updateRequest.pathVariable("id")).thenReturn(user1.getId());
        
        when(updateRequest
    			.bodyToMono(UserEntity.class))
    						.thenReturn(Mono.just(user1));
        
        userApiHandler
    			.update(updateRequest)
    				.subscribe(res -> 
    						verifySingleServerResponse(res,user1));
  
        
        
    }
//	  @Test
//	  void verifiesTypeAndMessage() {
//		  
//	        Throwable throwable = 
//	        		assertThrows(Exception.class, new Thrower()::throwsRuntime);
//
//	        assertAll(
//	            () -> assertEquals("My custom runtime exception", throwable.getMessage()),
//	            () -> assertNull(throwable.getCause())
//	        );
//	    }

}
