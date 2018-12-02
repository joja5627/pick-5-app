package io.pick5.web.service;

import reactor.core.publisher.Mono;

public interface GameLinesService {

	Mono<String> getBookmakerLines(String endpoint);

	Mono<String> getBookmakerLines(Mono<String> address);

}
