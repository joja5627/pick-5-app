package io.pick5.web.service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import io.pick5.web.domain.BovadaResponse;
import reactor.core.publisher.Mono;

@Service
public class GameLinesServiceImpl implements GameLinesService {

	private final Gson gson;

	@Autowired
	private AsyncHttpClient client;

	public GameLinesServiceImpl(AsyncHttpClient client, Gson gson) {
		this.client = client;
		this.gson = gson;
	}

	@Override
	public Mono<BovadaResponse> getBookmakerLines(String url, Map<String, String> queryParams) {
		return client.asyncGet(url, String.class, queryParams).transform(this::bovadaResponseBuilder);

	}

	private Mono<BovadaResponse> bovadaResponseBuilder(Mono<String> httpResponse) {
		return httpResponse.flatMap(responseBody -> Mono.just(BovadaResponse.builder().id(UUID.randomUUID().toString())
				.response(responseBody).timeStamp(LocalDateTime.now()).build()));

	}

	private <T> Mono<T> getJsonFromRequest(String body, Class<T> genericClassDeclaration) {
		try {
			return Mono.just(parseJson(body, genericClassDeclaration));
		} catch (JsonSyntaxException | IllegalStateException e) {
			return Mono.error(e);
		}
	}

	private <T> T parseJson(String body, Class<T> genericClassDeclaration) {
		return gson.fromJson(body, genericClassDeclaration);
	}

}
