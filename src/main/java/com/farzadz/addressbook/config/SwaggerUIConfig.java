package com.farzadz.addressbook.config;

import static com.farzadz.addressbook.config.RestEndpointConfig.SWAGGER_BASE_PATH;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SwaggerUIConfig {

  @RequestMapping(value = {SWAGGER_BASE_PATH, "/"})
  public String api() {
    return "redirect:/swagger-ui/";
  }
}
