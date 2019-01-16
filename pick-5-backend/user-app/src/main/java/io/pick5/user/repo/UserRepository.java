package io.pick5.user.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import io.pick5.domain.User;


@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
	

}
