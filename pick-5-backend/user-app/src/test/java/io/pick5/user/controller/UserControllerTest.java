package io.pick5.user.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import io.pick5.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Profile("integration")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
	
	@Autowired 
	ReactiveMongoTemplate template;
	
	@Autowired
	private WebTestClient webTestClient;
	
	private final String SAVE_PATH = "/api/v1/user/save/";

	private final String USER_PATH = "/api/v1/user/";
	
	private static String USERS_PATH = "/api/v1/users/";
	
	
	
	
	private final User createUser(String userName, String email) {
	
		User user = new User();
		
		user.setEmail(email);
		user.setUsername(userName);
		user.setActive(true);
		
		
		return user;
	
	}
	
	
	@Test
	public void templateTest() {

		User expectedUser = createUser("user1","email1");
		
		webTestClient
			.post()
				.uri(SAVE_PATH)
					.contentType(MediaType.APPLICATION_JSON_UTF8)
						.accept(MediaType.APPLICATION_JSON_UTF8)
							.body(Mono.just(expectedUser), User.class)
								.exchange().expectStatus().isOk();
		webTestClient
			.post()
				.uri(SAVE_PATH)
					.contentType(MediaType.APPLICATION_JSON_UTF8)
						.accept(MediaType.APPLICATION_JSON_UTF8)
							.body(Mono.just(createUser("user2","email2")), User.class)
								.exchange().expectStatus().isOk();
		
		

		Flux<User> queryResult  = template.find(Query.query(Criteria.where("email").is("email1")), User.class);
		
		List<User> userResult = queryResult.collectList().block();
		assertThat(userResult.size()).isEqualTo(1);
		assertThat(userResult.get(0)).isEqualTo(expectedUser);
		
	}
	
	@Test
	public void saveUser() {
		
		User user = createUser("name","email");
		
		webTestClient
			.post()
				.uri(SAVE_PATH)
					.contentType(MediaType.APPLICATION_JSON_UTF8)
						.accept(MediaType.APPLICATION_JSON_UTF8)
							.body(Mono.just(user), User.class)
								.exchange()
								
			.expectStatus().isOk()
				.expectBody()
					.jsonPath("$.id").exists()
						.jsonPath("$.username").isEqualTo("name")
							.jsonPath("$.email").isEqualTo("email");
        
	}
	
	@Test
	public void deleteUser() {
		
		
		User user = createUser("name","email");
		
		
		webTestClient
			.post()
				.uri(SAVE_PATH)
					.contentType(MediaType.APPLICATION_JSON_UTF8)
						.accept(MediaType.APPLICATION_JSON_UTF8)
							.body(Mono.just(user), User.class)
								.exchange();
		
		
		webTestClient
			.delete()
				.uri(USER_PATH + user.getId())
					.accept(MediaType.APPLICATION_JSON_UTF8)
						.exchange()
							.expectStatus().isOk();
        
	}
	public void getAllUsers() {
	
		    webTestClient.get().uri(USERS_PATH)
	                .accept(MediaType.APPLICATION_JSON_UTF8)
	                .exchange()
	                .expectStatus().isOk()
	                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
	                .expectBodyList(User.class);
	    
	}
	
	@Test
	public void updateUser() {
		
		User user = createUser("name","email");
		
		
		webTestClient
			.post()
				.uri(SAVE_PATH)
					.contentType(MediaType.APPLICATION_JSON_UTF8)
						.accept(MediaType.APPLICATION_JSON_UTF8)
							.body(Mono.just(user), User.class)
							.exchange().expectStatus().isOk();
		
		
		webTestClient
			.put()
				.uri(USER_PATH)
					.contentType(MediaType.APPLICATION_JSON_UTF8)
						.accept(MediaType.APPLICATION_JSON_UTF8)
							.body(Mono.just(user), User.class)
							.exchange().expectStatus().isOk()
							.expectBody()
								.jsonPath("$.id").exists()
									.jsonPath("$.username").isEqualTo("name")
										.jsonPath("$.email").isEqualTo("email");
        
	}
	

}

