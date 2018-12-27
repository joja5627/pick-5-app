package io.pick5.auth.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * X-Team WebFlux Authentication DTO for Querying on UUID.
 *
 * @Author - Adam InTae Gerard - https://www.linkedin.com/in/adamintaegerard/
 */

@Getter
@Setter
public class AuthenticatedUuid extends TokenAuth {
  private String id;
}
