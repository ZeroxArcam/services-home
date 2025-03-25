package com.pragma.hogar360.serviceshome.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Microservice Home")
                        .version("1.0.0")
                        .description("This microservice provides core functionalities for managing property listings. It allows users to define and store categories for houses, record and manage property locations, and publish house listings for interaction. The service is designed to be extensible, with future enhancements planned to incorporate additional features and capabilities related to property management and real estate interactions.")
                        .contact(new Contact()
                                .name("Ciro Alfonso Pallares Fragozo"))
                                //.email("soporte@empresa.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://github.com/ZeroxArcam")));
    }
}