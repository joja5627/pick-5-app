package io.pick5.auth.handler;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import io.pick5.auth.domain.MongoUser;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface UserMongoReactiveRepository extends ReactiveMongoRepository<MongoUser, String> {

  Mono<MongoUser> findOneByUsername(String username);

  @Override
Flux<MongoUser> findAll();
}
