package io.pick5.component;

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

//}
