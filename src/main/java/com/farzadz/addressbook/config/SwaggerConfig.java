package com.farzadz.addressbook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2).select()
        .apis(RequestHandlerSelectors.basePackage("com.farzadz.addressbook.controller")).paths(PathSelectors.any())
        .build().apiInfo(meta());
  }

  private ApiInfo meta() {
    ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
    return apiInfoBuilder.title("Address Book ReST API").description("Spring Boot ReST API for a minimal addressbook")
        .version("1.0").license("MIT License").build();
  }
}
