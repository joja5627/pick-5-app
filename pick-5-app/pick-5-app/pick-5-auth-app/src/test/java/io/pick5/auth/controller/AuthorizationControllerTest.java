package io.pick5.auth.controller;



import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import io.pick5.web.tags.UnitTest;


//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//@WebFluxTest(AuthorizationController.class)
@UnitTest
public class AuthorizationControllerTest {

	   
       private WebTestClient webTestClient;

       @Before
       public void setUp() {
    	   webTestClient = 
    			   WebTestClient.bindToController(new AuthorizationController()).build(); 
       }
      
		
		@Test
		public void testRegisterEndpoint() {
	
			webTestClient
					.post()
					.uri("/register")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.accept(MediaType.APPLICATION_JSON_UTF8)
					//.body(Mono.just(repoRequest), RepoRequest.class)
					.exchange()
					.expectStatus().isOk()
					.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8);
					//.expectBody()
					//.jsonPath("$.name").isNotEmpty()
					//.jsonPath("$.name").isEqualTo("test-webclient-repository");
		}
	
	
}
