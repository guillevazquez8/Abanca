package com.proyecto.abanca;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;


@Configuration
public class SpringFoxConfig {

    HttpAuthenticationScheme authenticationScheme = HttpAuthenticationScheme
            .JWT_BEARER_BUILDER
            .name("JWT Token")
            .build();

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(Collections.singletonList(authenticationScheme));
    }

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("ABANCA API")
                .version("1.0.0")
                .description("The best API for banking operations")
                .contact(new Contact("Guille", "www.guille.com","guille@email.com"))
                .build();
    }

}
