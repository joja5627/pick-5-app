package io.pick5.auth.handler;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import io.pick5.auth.domain.MongoEmail;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface EmailMongoReactiveRepository extends ReactiveMongoRepository<MongoEmail, String> {

  Mono<MongoEmail> findOneByAddress(String address);

  @Override
Flux<MongoEmail> findAll();
}