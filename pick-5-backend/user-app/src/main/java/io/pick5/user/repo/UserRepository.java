package io.pick5.user.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import io.pick5.domain.User;


@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
    public Optional<User> findByUsername(String username);

    public Optional<User> findByEmail(String email);

}
