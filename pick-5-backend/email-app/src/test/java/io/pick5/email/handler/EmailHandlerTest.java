package io.pick5.email.handler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import io.pick5.domain.User;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class EmailHandlerTest {
	
	@Autowired
	WebTestClient webTestClient;
	
	@Value("${path.email}")
	private String emailPath;
	
	 @Test
	    public void testCreateContactPerson__isOK() {

		 User user = User.builder().username("username1").id("id")
		  		  .email("test.dest@nutpan.com").confirmationCode("code").build();

	        webTestClient.post().uri(emailPath)
	                .accept(MediaType.APPLICATION_JSON_UTF8)
	                .body(Mono.just(user), User.class)
	                .exchange()
	                .expectStatus().isOk();
	                
	               
	    }
	

}
