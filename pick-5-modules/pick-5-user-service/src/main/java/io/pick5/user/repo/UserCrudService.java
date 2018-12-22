package io.pick5.user.repo;

import java.util.Optional;

import io.pick5.user.domain.UserEntity;

/**
 * User security operations like login and logout, and CRUD operations on {@link User}.
 * 
 * @author jerome
 *
 */
public interface UserCrudService {

  UserEntity save(UserEntity user);

  Optional<UserEntity> find(String id);

  Optional<UserEntity> findByUsername(String username);
}
