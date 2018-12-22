package io.pick5.user.in.memory.service;



import static java.util.Optional.ofNullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.pick5.user.domain.UserEntity;
import io.pick5.user.repo.UserCrudService;

@Service
final class InMemoryUsers implements UserCrudService {

  Map<String, UserEntity> users = new HashMap<>();

  @Override
  public UserEntity save(final UserEntity user) {
    return users.put(user.getId(), user);
  }

  @Override
  public Optional<UserEntity> find(final String id) {
    return ofNullable(users.get(id));
  }

  @Override
  public Optional<UserEntity> findByUsername(final String username) {
    return users
      .values()
      .stream()
      .filter(u -> Objects.equals(username, u.getUsername()))
      .findFirst();
  }
}
