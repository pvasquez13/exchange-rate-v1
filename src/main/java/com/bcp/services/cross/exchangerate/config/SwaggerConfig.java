package com.bcp.services.cross.exchangerate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

  @Bean
  public Docket productApi() {
    return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.bcp.services.cross.expose.web"))
            .paths(PathSelectors.any())
            .build()
            .useDefaultResponseMessages(false)
            .apiInfo(apiInfo());
  }

  private ApiInfo apiInfo() {
    return new ApiInfo(
            "Exchange Rate API",
            "API that performs exchange rate",
            "1.0",
            null,
            new Contact("Peter Vasquez Sotelo", null, "vasquez.soft13@gmail.com"),
            null, null, Collections.emptyList());
  }

}
