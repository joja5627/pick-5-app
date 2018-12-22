package io.pick5.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.pick5.auth.CONSTANTS;
import io.pick5.auth.domain.ForMagicLink;
import io.pick5.auth.service.PasswordlessAuthenticator;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class PasswordlessReactiveRedisController {

//  @Autowired
//  SendGridEmail sendGridEmail;

  @Autowired
  PasswordlessAuthenticator passwordlessAuthenticator;

  @PostMapping(CONSTANTS.AUTH_LOGIN_ENDPOINT)
  public String generateMagicLink(@RequestBody ForMagicLink forMagicLink) {
	  
	  
    try {
      String username = forMagicLink.getUsername();
      forMagicLink.getEmail();
      passwordlessAuthenticator.generateToken(username);
      return  passwordlessAuthenticator.generateToken(username).toString();
    } catch (Exception ex) {
      log.error("Exception sending magic email: " + ex);
    }
    return "Error Occurred";
  }

}
