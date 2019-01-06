package io.pick5.user.handler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import io.pick5.domain.User;
import io.pick5.registration.handler.RegistrationHandler;
import io.pick5.tags.IntegrationTest;
import io.pick5.test.utils.HandlersHelper;

@IntegrationTest
@DisplayName("UserApiHandlerTest Integration Tests")
public class RegistrationHandlerTest {
	
	@Autowired
	private RegistrationHandler userApiHandler;
	
	
	@BeforeEach
	void before() {
		
		   final ServerRequest
   						serverRequest = 
   								mock(ServerRequest.class);		
		
	    
	}
	
	
	@SuppressWarnings("unchecked")
	private void verifyEntitiesResponse(final ServerResponse serverResponse, List<User> users) {

	        assertThat(serverResponse.statusCode(), is(HttpStatus.OK));
	        
	       
			final List<User> entitiesResponse = 
	        		(List<User>) HandlersHelper.extractEntities(serverResponse, User.class);
	        
	        assertThat(entitiesResponse)
	        	.containsExactlyInAnyOrderElementsOf(users);
       
	   
	}
	
	

	 private void verifySingleServerResponse(final ServerResponse serverResponse, User entity) {

	        assertThat(serverResponse.statusCode(), is(HttpStatus.OK));
	        
	        final User entityResponse = 
	        		HandlersHelper.extractEntity(serverResponse, User.class);
	        
	        assertThat(entityResponse)
	        	.isEqualToComparingFieldByField(entity);
	   
	}
	 private void verifyServerResponseOk(final ServerResponse serverResponse) {

	        assertThat(serverResponse.statusCode(), is(HttpStatus.OK));
	   
	}
	
	

}
