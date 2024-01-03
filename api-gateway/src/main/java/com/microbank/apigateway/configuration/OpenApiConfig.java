package com.microbank.apigateway.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
    @Bean
    OpenAPI openApi() {
        return new OpenAPI()
            .info(new Info()
            .title("API Gateway Configuration")
            .description("An API that can manage queries")
            .version("v1.0"));
    }

}
