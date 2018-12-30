package io.pick5.web.repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;

import io.pick5.web.domain.BovadaResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class BovadaResponseRepository {

	ReactiveRedisOperations<String, BovadaResponse> template;

	public BovadaResponseRepository(ReactiveRedisOperations<String, BovadaResponse> template) {
		this.template = template;
	}

	Flux<BovadaResponse> findAll() {
		return template.<String, BovadaResponse>opsForHash().values("bovadaResponses");
	}

	Mono<BovadaResponse> findById(String id) {
		return template.<String, BovadaResponse>opsForHash().get("bovadaResponses", id);
	}

	Mono<BovadaResponse> findByDate(LocalDateTime dateTime) {
		return template.<String, BovadaResponse>opsForHash().get("bovadaResponses", dateTime);
	}

	public Mono<BovadaResponse> findWithinRange(LocalDateTime fromDateTime, LocalDateTime toDateTime) {
		return findAll().filter(response -> 1 < ChronoUnit.DAYS.between(fromDateTime, toDateTime)).elementAt(0);
	}

//	Flux<BovadaResponse> deleteWithinRange(LocalDateTime fromDateTime, LocalDateTime toDateTime) {
//
//		return findAll().filter(response -> 1 < ChronoUnit.DAYS.between(fromDateTime, toDateTime));
//	}

	Mono<BovadaResponse> save(BovadaResponse bovadaResponse) {
		if (bovadaResponse.getId() != null) {
			String id = UUID.randomUUID().toString();
			bovadaResponse.setId(id);
		}
		return template.<String, BovadaResponse>opsForHash()
				.put("bovadaResponses", bovadaResponse.getId(), bovadaResponse).log().map(p -> bovadaResponse);

	}

	Mono<Void> deleteById(String id) {
		return template.<String, BovadaResponse>opsForHash().remove("bovadaResponses", id)
				.flatMap(p -> Mono.<Void>empty());
	}

	Mono<Void> deleteByDate(LocalDateTime dateTime) {
		return template.<String, BovadaResponse>opsForHash().remove("bovadaResponses", dateTime)
				.flatMap(p -> Mono.<Void>empty());
	}
//	Mono<Void> deleteAll() {
//		return findAll().map(response ->   deleteById(response.id));
//		
//	}

}
