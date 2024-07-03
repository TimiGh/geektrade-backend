package com.geektrade.geektradebackend.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@EnableSwagger2
public class SwaggerConfig {

    public static final String AUTHORIZATION = "AUTHORIZATION";

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("RENATALIBRARY")
                .description("Renata LIBRARY")
                .license("Vlad renatatsadnsaklfjDGAESHFMajklh")
                .version("1.0.0").contact(new Contact("Vlad Renata", ".com", "sad@gmail.com"))
                .build();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/**"))
                .build()
                .enable(true)
                .apiInfo(apiInfo())
                .securityContexts(Lists.newArrayList(securityContext()))
                .securitySchemes(Lists.newArrayList(apiKey()));
    }

    private ApiKey apiKey() {
        return new ApiKey(AUTHORIZATION, AUTHORIZATION, "header");
    }

    @Bean
    SecurityConfiguration security() {
        return new SecurityConfiguration(null,
                null,
                null,
                null,
                null,
                null,
                Boolean.FALSE);
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/.*"))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{authorizationScope};
        return Lists.newArrayList(
                new SecurityReference(AUTHORIZATION, authorizationScopes));
    }

}