package io.pick5.web.service.handlers;

import java.time.LocalDateTime;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import io.pick5.constants.BovadaConstants;
import io.pick5.web.domain.BovadaResponse;
import io.pick5.web.repository.BovadaResponseRepository;
import io.pick5.web.service.GameLinesService;
import reactor.core.publisher.Mono;

@Component
public class ApiHandler {

	private final ErrorHandler errorHandler;
	private final GameLinesService gameLinesService;
	private final BovadaResponseRepository bovadaResponseRepository;
	private final String bovadaEndpoint;
	private static final Logger logger = LoggerFactory.getLogger(ApiHandler.class);

	@Autowired
	public ApiHandler(final ErrorHandler errorHandler, GameLinesService gameLinesService,
			@Value("${bovada.endpoint}") final String bovadaLinesUrl,
			BovadaResponseRepository bovadaResponseRepository) {
		this.errorHandler = errorHandler;
		this.gameLinesService = gameLinesService;
		this.bovadaResponseRepository = bovadaResponseRepository;
		bovadaEndpoint = bovadaLinesUrl;

	}

	public Mono<ServerResponse> getBovadaLines(final ServerRequest request) {

		LocalDateTime currentTime = LocalDateTime.now();

		LocalDateTime yesterday = currentTime.minusDays(1);

		Mono<BovadaResponse> cachedResponse = bovadaResponseRepository.findWithinRange(currentTime, yesterday);

		if (Objects.nonNull(cachedResponse)) {
			
			logger.info("Returning Cached Response %s",cachedResponse.toString());
			
			return cachedResponse
						.transform(this::serverResponse)
							.onErrorResume(errorHandler::throwableError);
		} else {
			logger.info("Returning Evicting Cache",cachedResponse.toString());
			
			return gameLinesService.
						getBookmakerLines(bovadaEndpoint, BovadaConstants.QUERY_PARAMS)
							.transform(this::serverResponse)
								.onErrorResume(errorHandler::throwableError);
		}

	}

	Mono<ServerResponse> serverResponse(final Mono<BovadaResponse> response) {
		return response
				.flatMap(bovadaResponse -> ServerResponse.ok().syncBody(bovadaResponse.getResponse().toString()));
	}
}