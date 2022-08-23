package com.example.droneapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig {

    private static  final String AUTHORIZATION_HEADER = "Authorization";
    private ApiKey apikey(){
        return  new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    }

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.example.droneapi"))
                .build()
                .apiInfo(apiDetails())
                .securitySchemes(Arrays.asList(apikey()));
    }

    private ApiInfo apiDetails(){
        return new ApiInfo(
                "Drone Api",
                "Drone dispatch Api",
                "1.0",
                "Musala task",
                new springfox.documentation.service.Contact("Musala",
                        "#",
                        "sinebi.innazo@mail.com"),
                "Api License",
                "#",
                Collections.emptyList()
        );
    }

}

