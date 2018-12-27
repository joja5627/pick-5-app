package io.pick5.auth.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;

import io.pick5.auth.CONSTANTS;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;



@Slf4j
@Data
@Getter
@Setter
public class RedisAuthentication {

  @Id
  private String username;
  private String token;
  private long expires;

  public RedisAuthentication(String username, String token) {
    this.username = username;
    this.token = token;
    this.expires = new Date().getTime() + CONSTANTS.FIFTEEN_MINS;
  }

  //Only Use When Deserializing From Redis String
  public RedisAuthentication(String username, String token, long expires) {
    this.username = username;
    this.token = token;
    this.expires = expires;
  }

  public boolean hasExpired() {
    log.info("Checking if token has expired!");
    return this.expires > new Date().getTime() + CONSTANTS.FIFTEEN_MINS;
  }

}
