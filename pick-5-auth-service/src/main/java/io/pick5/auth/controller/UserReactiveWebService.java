package io.pick5.auth.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.pick5.auth.domain.MongoEmail;
import io.pick5.auth.domain.MongoUser;
import io.pick5.auth.handler.EmailMongoReactiveRepository;
import io.pick5.auth.handler.EmailRedisReactiveRepository;
import io.pick5.auth.handler.UserMongoReactiveRepository;
import io.pick5.auth.handler.UserRedisReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class UserReactiveWebService {

  @Autowired
  UserMongoReactiveRepository userMongoReactiveRepository;

  @Autowired
  UserRedisReactiveRepository userRedisReactiveRepository;

  @Autowired
  EmailMongoReactiveRepository emailMongoReactiveRepository;

  @Autowired
  EmailRedisReactiveRepository emailRedisReactiveRepository;


  public Mono<MongoUser> findOneUserById(String username) {
    return userRedisReactiveRepository.findOneByUsername(username)
        .log()
        .flatMap(user -> Mono.just(new MongoUser(user.getUsername(), user.getName(), user.getPhone(), user.getEmail(), user.getContacts())))
        .switchIfEmpty(userMongoReactiveRepository.findOneByUsername(username));
  }

  public void saveUser(String username, String name, String phone, String email) {
    try {
      MongoUser userToSave = new MongoUser(username, name, phone, new MongoEmail(email), null);
      userMongoReactiveRepository.save(userToSave).block();
      userRedisReactiveRepository.save(userToSave).block();
    } catch (Exception ex) {
      log.info("Exception: " + ex);
    }
  }

  public void deleteUser(String username) {
    userRedisReactiveRepository.delete(username);
    userMongoReactiveRepository.deleteById(username)
        .log()
        .block();
  }

  public void updateUser(String username, String name, String phone, String newAddress) {
    emailMongoReactiveRepository.deleteById(username).log().block();
    emailRedisReactiveRepository.delete(username);

    userMongoReactiveRepository.findOneByUsername(username)
        .log()
        .flatMap(mongoUser -> Mono.just(userMongoReactiveRepository.save(mongoUser.update(name, phone, newAddress, null))))
        .block();

    userMongoReactiveRepository.findOneByUsername(username)
        .log()
        .flatMap(mongoUser -> userMongoReactiveRepository.save(mongoUser.update(name, phone, newAddress, null)))
        .block();
  }

  //Mono not Flux here
  public Mono<List<MongoUser>> findAllUsers() {
    return userMongoReactiveRepository.findAll()
        .log()
        .flatMap(user -> Mono.just(new MongoUser(user.getUsername(), user.getName(), user.getPhone(), user.getEmail(), user.getContacts())))
        .collectList()
        .flatMap(Mono::just);
  }

}
