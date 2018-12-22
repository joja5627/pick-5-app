package io.pick5.auth.handler;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import io.pick5.auth.domain.CustomResponse;
import io.pick5.auth.domain.MongoEmail;
import io.pick5.auth.domain.MongoUser;
import io.pick5.auth.domain.UpdateRequestBody;
import io.pick5.auth.domain.UuidRequestBody;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class FunctionalRouterWebHandler {

  @Autowired
  UserMongoReactiveRepository userMongoReactiveRepository;

  @Autowired
  UserRedisReactiveRepository userRedisReactiveRepository;

  @Autowired
  EmailMongoReactiveRepository emailMongoReactiveRepository;

  @Autowired
  EmailRedisReactiveRepository emailRedisReactiveRepository;

  /**
   * Helpers.
   */

  private MongoUser updateMongoUser(MongoUser u, UpdateRequestBody requestBody) {
    return u.update(requestBody.getUsername(), requestBody.getName(), requestBody.getPhone(), null);
  }

  private MongoUser innerMongoUser(UpdateRequestBody requestBody) {
    return new MongoUser(requestBody.getUsername(), requestBody.getName(), requestBody.getPhone(), new MongoEmail(requestBody.getEmail()), null);
  }

  private Mono<ServerResponse> fail(Exception ex) {
    log.error("Exception:" + ex);
    return ServerResponse.ok().body(Mono.just(new CustomResponse(
        new MongoUser(null, null, null, null, null), "Failure!")), CustomResponse.class);
  }

  private Mono<ServerResponse> success(CustomResponse customResponse) {
    return ServerResponse.ok().body(Mono.just(customResponse), CustomResponse.class);
  }

  private Mono<MongoUser> mongoUser(MongoUser user) {
    return Mono.just(new MongoUser(user.getUsername(), user.getName(), user.getPhone(), user.getEmail(), user.getContacts()));
  }

  /**
   * Handlers.
   */

  public Mono<ServerResponse> getOneUser(ServerRequest request) {
    try {
      return request.bodyToMono(UuidRequestBody.class)
          .log()
          .flatMap(body -> {
            final String id = body.getId();
            return Mono.just(userRedisReactiveRepository.findOneByUsername(id)
                .log()
                .switchIfEmpty(userMongoReactiveRepository.findOneByUsername(id))
                .block());
          })
          .flatMap(user -> success(new CustomResponse(user, "Success!")));
    } catch (Exception ex) {
      return fail(ex);
    }
  }

  public Mono<ServerResponse> saveOneUser(ServerRequest request) {
    try {
      return request.bodyToMono(UpdateRequestBody.class)
          .log()
          .flatMap(body -> {
                MongoUser innerUser = innerMongoUser(body);
                userMongoReactiveRepository.save(innerUser).block();
                userRedisReactiveRepository.save(innerUser).block();
                return Mono.just(innerUser);
              }
          )
          .flatMap(user -> success(new CustomResponse(user, "Success!")));
    } catch (Exception ex) {
      return fail(ex);
    }
  }

  public Mono<ServerResponse> deleteOneUser(ServerRequest request) {
    try {
      return request.bodyToMono(UuidRequestBody.class)
          .log()
          .flatMap(body -> {
            userMongoReactiveRepository.deleteById(body.getId());
            userRedisReactiveRepository.delete((body.getId()));
            return Mono.just(new MongoUser(body.getId(), null, null, null, null));
          })
          .flatMap(user -> success(new CustomResponse(user, "Success!")));
    } catch (Exception ex) {
      return fail(ex);
    }
  }

  public Mono<ServerResponse> updateOneUser(ServerRequest request) {
    try {
      return request.bodyToMono(UpdateRequestBody.class)
          .log()
          .flatMap(body -> userMongoReactiveRepository.findOneByUsername(body.getUsername())
              .flatMap(u -> userMongoReactiveRepository.save(updateMongoUser(u, body)))
              .flatMap(u -> {
                userRedisReactiveRepository.delete(u.getUsername());
                userRedisReactiveRepository.save(u);
                return Mono.just(u);
              }))
          .flatMap(user -> success(new CustomResponse(user, "Success!")));
    } catch (Exception ex) {
      return fail(ex);
    }
  }

  public Mono<ServerResponse> getAllUsers(ServerRequest request) {
    try {
      List<MongoUser> users = userMongoReactiveRepository.findAll()
          .log()
          .flatMap(user -> mongoUser(user))
          .collectList()
          .flatMap(Mono::just).block();
      return success(new CustomResponse(users, "Success!"));
    } catch (Exception ex) {
      return fail(ex);
    }
  }
}
