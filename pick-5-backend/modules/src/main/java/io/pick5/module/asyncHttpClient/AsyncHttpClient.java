
package io.pick5.module.asyncHttpClient;

import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class AsyncHttpClient {


	private final WebClient webClient;

	public AsyncHttpClient() {
		this(WebClient.builder().filter(logRequest()).filter(logResponse()).build());
	}

	AsyncHttpClient(WebClient webClient) {
		this.webClient = webClient;

	}

	public <T> Mono<T> asyncRequest(String url, Class<T> clazz, Map<String, String> uriVariables,HttpMethod method) {
		return webClient.method(method)
				.uri(url, uriVariables).retrieve()
				.onStatus(HttpStatus::is4xxClientError, response -> Mono.error(getException(response)))
				.onStatus(HttpStatus::is5xxServerError, response -> Mono.error(getException(response)))
				.bodyToMono(clazz);
	}

	public <T> Mono<T> asyncRequest(String url, Class<T> clazz,HttpMethod method) {
		return webClient.method(method)
				.uri(url).retrieve()
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
			log.info("Response status {}", clientResponse.statusCode());
			return Mono.just(clientResponse);
		});
	}

	private static ExchangeFilterFunction logRequest() {
		return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
			log.info("Request: {} {}", clientRequest.method(), clientRequest.url());
			clientRequest.headers()
					.forEach((name, values) -> values.forEach(value -> log.info("{}={}", name, value)));
			return Mono.just(clientRequest);
		});
	}

}
