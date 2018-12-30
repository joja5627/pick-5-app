package io.pick5.user.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import io.pick5.user.domain.UserEntity;


@Repository
public interface UserRepository extends ReactiveMongoRepository<UserEntity, String> {

//    Flux<UserEntity> findByAgeBetween(int start, int end);

//    @Query("{'age':{'$gte':20,'$lte':45}}")
//    Flux<UserEntity> oldUser();
}
