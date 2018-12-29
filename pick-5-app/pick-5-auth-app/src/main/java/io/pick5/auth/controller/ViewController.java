package io.pick5.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ViewController {

  @GetMapping("/")
  public String welcome() {
    return "index";
  }

}
