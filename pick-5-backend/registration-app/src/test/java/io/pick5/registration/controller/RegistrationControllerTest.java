package io.pick5.registration.controller;


import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import io.pick5.domain.User;
import io.pick5.registration.service.EmailServiceProxyRetryable;
import io.pick5.registration.service.UserServiceProxyRetryablImpl;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegistrationControllerTest {
	
	@Autowired
	private WebTestClient webTestClient;
	
	@Mock
	private UserServiceProxyRetryablImpl userService;
	
	@Mock
	private EmailServiceProxyRetryable emailService;
	
	
	
	@Test
	public void testRegisterUser() {
		
		User user = User.builder().build();
		
		List<User> users = List.of(User.builder().build(),
					User.builder().build(),
						User.builder().build(),
							User.builder().build());
		
		
		
		
		when(userService.getCurrentUsers())
			.thenReturn(Flux.fromIterable(users));
		
		when(userService.getCurrentUsers())
			.thenReturn(Flux.fromIterable(users));
		

		webTestClient
			.post()
			.uri("/register")
			.contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)
            .body(Mono.just(user), User.class)
            .exchange()
            .expectStatus().isOk()
			.expectBody();

	}

}
