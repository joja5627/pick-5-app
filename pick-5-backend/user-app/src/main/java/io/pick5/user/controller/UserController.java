package io.pick5.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.pick5.domain.ErrorResponse;
import io.pick5.domain.User;
import io.pick5.user.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class UserController {
	
    @Autowired
    private UserRepository userRepository;

    @GetMapping("${path.get_all}")
    public Flux<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("${path.save}")
    public Mono<User> createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("${path.userById}")
    public Mono<ResponseEntity<User>> getUserById(@PathVariable(value = "id") String userId) {
        return userRepository.findById(userId)
                .map(savedUser -> ResponseEntity.ok(savedUser))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
   

    @PutMapping("${path.user}")
    public Mono<ResponseEntity<User>> updateUser( @Valid @RequestBody User user) {
    	
    	log.info(user.getId());
    	
        return userRepository.findById(user.getId())
                .flatMap(existingUser -> {
                    return userRepository.save(existingUser);
                })
                .map(updateUser -> new ResponseEntity<>(updateUser, HttpStatus.OK))
                	.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("${path.userById}")
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable(value = "id") String userId) {
        return userRepository.findById(userId)
                .flatMap(existingUser ->
                        userRepository.delete(existingUser)
                            .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity handleDuplicateKeyException(DuplicateKeyException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse("A Tweet with the same text already exists"));
    }

}
