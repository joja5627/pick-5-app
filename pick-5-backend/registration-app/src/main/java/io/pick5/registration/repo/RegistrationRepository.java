package io.pick5.registration.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import io.pick5.domain.Registration;

@Repository
public interface RegistrationRepository extends ReactiveMongoRepository<Registration, String> {

}
