package io.pick5.auth.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * X-Team WebFlux Authentication DTO to Validate MagicLink Credentials.
 *
 * @Author - Adam InTae Gerard - https://www.linkedin.com/in/adamintaegerard/
 */

@Getter
@Setter
public class TokenAuth {
  private String username;
  private String token;
}
