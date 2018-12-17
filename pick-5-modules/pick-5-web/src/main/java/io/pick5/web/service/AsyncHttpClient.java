
package io.pick5.web.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class AsyncHttpClient {

	private static final Logger logger = LoggerFactory.getLogger(AsyncHttpClient.class);

	private final WebClient webClient;

	public AsyncHttpClient() {
		this(WebClient.builder().filter(logRequest()).filter(logResponse()).build());
	}

	AsyncHttpClient(WebClient webClient) {
		this.webClient = webClient;

	}

	public <T> Mono<T> asyncGet(String url, Class<T> clazz, Map<String, String> uriVariables) {
		return webClient.get().uri(url, uriVariables).retrieve()
				.onStatus(HttpStatus::is4xxClientError, response -> Mono.error(getException(response)))
				.onStatus(HttpStatus::is5xxServerError, response -> Mono.error(getException(response)))
				.bodyToMono(clazz);
	}

	public <T> Mono<T> asyncGet(String url, Class<T> clazz) {
		return webClient.get().uri(url).retrieve()
				.onStatus(HttpStatus::is4xxClientError, response -> Mono.error(getException(response)))
				.onStatus(HttpStatus::is5xxServerError, response -> Mono.error(getException(response)))
				.bodyToMono(clazz);
	}

	private RuntimeException getException(ClientResponse response) {
		return new RuntimeException(
				String.format("Request for cloud config failed: HTTP %d", response.statusCode().value()));
	}

	private static ExchangeFilterFunction logResponse() {
		return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
			logger.info("Response status {}", clientResponse.statusCode());
			return Mono.just(clientResponse);
		});
	}

	private static ExchangeFilterFunction logRequest() {
		return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
			logger.info("Request: {} {}", clientRequest.method(), clientRequest.url());
			clientRequest.headers()
					.forEach((name, values) -> values.forEach(value -> logger.info("{}={}", name, value)));
			return Mono.just(clientRequest);
		});
	}

}
