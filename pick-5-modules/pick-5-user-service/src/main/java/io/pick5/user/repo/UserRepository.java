
package io.pick5.user.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import io.pick5.user.domain.User;
import reactor.core.publisher.Mono;

/**
 *
 * @author hantsy
 */
public interface UserRepository extends ReactiveMongoRepository<User, String> {

    Mono<User> findByUsername(String username);
    
    Mono<User> findByEmail(String email);
    
    @Override
	Mono<User> findById(String id);
}
