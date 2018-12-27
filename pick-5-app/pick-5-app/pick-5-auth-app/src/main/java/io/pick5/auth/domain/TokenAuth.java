package io.pick5.auth.domain;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TokenAuth {
  private String username;
  private String token;
}
