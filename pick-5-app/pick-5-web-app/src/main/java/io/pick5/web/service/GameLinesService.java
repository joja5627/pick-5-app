package io.pick5.web.service;

import java.util.Map;

import io.pick5.web.domain.BovadaResponse;
import reactor.core.publisher.Mono;

public interface GameLinesService {

	Mono<BovadaResponse> getBookmakerLines(String url, Map<String, String> queryParams);

}
