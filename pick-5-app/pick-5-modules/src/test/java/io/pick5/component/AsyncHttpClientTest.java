package io.pick5.component;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;

import com.google.gson.Gson;

import io.pick5.module.asyncHttpClient.AsyncHttpClient;


@Tag("UnitTest")
@SpringBootTest(classes = AsyncHttpClient.class)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class AsyncHttpClientTest {
	private static final String SOMEURL = "http://someurl";
	private static final String DATA = "{}";
	
	private Gson gson = new Gson();
	
	private WebClient webClient = mock(WebClient.class);
	
	private WebClient.RequestHeadersUriSpec requestBodyUriSpec = mock(WebClient.RequestBodyUriSpec.class);
	
	private WebClient.ResponseSpec responseSpec = mock(WebClient.ResponseSpec.class);

//	@Test
//	void shouldReturnJsonObjectOnGetCall() {
//
//	     AsyncHttpClient httpGetClient = new AsyncHttpClient(webClient);
//	     when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just(DATA));
//	     
//	     StepVerifier
//	     		.create(httpGetClient.asyncRequest(SOMEURL, JsonObject.class,HttpMethod.GET))
//	     			.expectSubscription()
//	     				.expectNext(gson.fromJson(DATA, JsonObject.class))
//	     					.verifyComplete();
//	}

//	@Test
//	void shouldReturnMonoErrorOnGetCall() {
//
//		AsyncHttpClient httpGetClient = new AsyncHttpClient(webClient);
//		when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just("some wrong data"));
//		StepVerifier.create(httpGetClient.asyncRequest(SOMEURL, JsonObject.class,HttpMethod.GET)).expectSubscription()
//				.expectError(JsonSyntaxException.class).verify();
//	}

//	private void mockWebClientDependantObject() {
//		doReturn(requestBodyUriSpec).when(webClient).get();
//		when(requestBodyUriSpec.uri(SOMEURL)).thenReturn(requestBodyUriSpec);
//		doReturn(responseSpec).when(requestBodyUriSpec).retrieve();
//		doReturn(responseSpec).when(responseSpec).onStatus(any(), any());
//		doReturn(responseSpec).when(responseSpec).onStatus(any(), any());
//	}

}
