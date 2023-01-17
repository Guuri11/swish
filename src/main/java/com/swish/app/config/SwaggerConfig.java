package com.swish.app.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI customizeOpenAPI() {

    final String securitySchemeName = "bearerAuth";
    return new OpenAPI()
        .info(
            new Info()
                .title("Swish Backend REST API")
                .contact(new Contact().email("dev@swish.com")
                    .name("Sergio"))
                .description("REST API Definition of Swish Platform")
                .version("1.0.0")
        )
        .addSecurityItem(new SecurityRequirement()
            .addList(securitySchemeName))
        .components(new Components()
            .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                .name(securitySchemeName)
                .type(Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")));
  }
}
