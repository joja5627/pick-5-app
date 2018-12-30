package io.pick5.web.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import com.google.gson.Gson;

import io.pick5.web.tags.UnitTest;

@UnitTest
public class AsyncHttpClientTest {
	private static final String SOMEURL = "http://someurl";
	private static final String DATA = "{}";
	private Gson gson = new Gson();
	private WebClient webClient = mock(WebClient.class);
	private WebClient.RequestHeadersUriSpec requestBodyUriSpec = mock(WebClient.RequestBodyUriSpec.class);
	private WebClient.ResponseSpec responseSpec = mock(WebClient.ResponseSpec.class);

	@Test
	void shouldReturnJsonObjectOnGetCall() {
		// given
//	        mockWebClientDependantObject();
//	        AsyncHttpClient httpGetClient = new AsyncHttpClient(webClient);
//	        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just(DATA));
//
//	        //when/then
//	        StepVerifier.create(httpGetClient.asyncGet(SOMEURL, JsonObject.class)).expectSubscription()
//	            .expectNext(gson.fromJson(DATA, JsonObject.class)).verifyComplete();
	}

	@Test
	void shouldReturnMonoErrorOnGetCall() {
		// given
//		mockWebClientDependantObject();
//		AsyncHttpClient httpGetClient = new AsyncHttpClient(webClient);
//		when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just("some wrong data"));
//
//		// when/then
//		StepVerifier.create(httpGetClient.asyncGet(SOMEURL, JsonObject.class)).expectSubscription()
//				.expectError(JsonSyntaxException.class).verify();
	}

	private void mockWebClientDependantObject() {
		doReturn(requestBodyUriSpec).when(webClient).get();
		when(requestBodyUriSpec.uri(SOMEURL)).thenReturn(requestBodyUriSpec);
		doReturn(responseSpec).when(requestBodyUriSpec).retrieve();
		doReturn(responseSpec).when(responseSpec).onStatus(any(), any());
		doReturn(responseSpec).when(responseSpec).onStatus(any(), any());
	}

}
