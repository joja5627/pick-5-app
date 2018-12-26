package io.pick5.user.handler;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import io.pick5.domain.user.UserEntity;
import io.pick5.user.repo.UserRepository;
import io.pick5.user.utils.CheckUtil;
import reactor.core.publisher.Mono;

@Component
public class UserApiHandler {

    private final UserRepository userRepository;

    @Autowired
    public UserApiHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ok().contentType(APPLICATION_JSON_UTF8)
                .body(userRepository.findAll(), UserEntity.class);
    }
    public Mono<ServerResponse> findById(ServerRequest request) {
    	
    	String id = request.pathVariable("id");
    	 
        return ok().contentType(APPLICATION_JSON_UTF8)
                .body(userRepository.findById(id), UserEntity.class);
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<UserEntity> userMono = request.bodyToMono(UserEntity.class);

        return userMono.flatMap(newUser -> {
            CheckUtil.checkName(newUser.getUsername());

            return ok().contentType(APPLICATION_JSON_UTF8)
                    .body(userRepository.save(newUser), UserEntity.class);
        });
    }

    public Mono<ServerResponse> update(ServerRequest request) {
    	
        Mono<UserEntity> userMono = request.bodyToMono(UserEntity.class);
        
        String id = request.pathVariable("id");

        return userMono.flatMap(updateUser -> {
            CheckUtil.checkName(updateUser.getUsername());

            return ok().contentType(APPLICATION_JSON_UTF8)
                    .body(userRepository.findById(id).flatMap(oldUser -> {
                        BeanUtils.copyProperties(updateUser, oldUser);
                        oldUser.setId(id);

                        return userRepository.save(oldUser);
                    }), UserEntity.class);
        });
    }
    public Mono<ServerResponse> delete(ServerRequest request) {
        String id = request.pathVariable("id");

        return userRepository
        			.findById(id)
        				.flatMap(user -> 
        					userRepository.delete(user)
        						.then(ServerResponse.ok().build()))
        							.switchIfEmpty(notFound().build());
    }	
}
