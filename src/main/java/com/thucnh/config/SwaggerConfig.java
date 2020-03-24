package com.thucnh.config;

import com.google.common.base.Predicate;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

   @Bean
    public Docket postsApi() {
       ParameterBuilder aParameterBuilder = new ParameterBuilder();
       aParameterBuilder.name("Authorization")
               .modelRef(new ModelRef("string"))
               .parameterType("header")
               .defaultValue("Basic YWRtaW46YWRtaW4=")
               .required(true)
               .build();
       List<Parameter> aParameters = new ArrayList<>();
       aParameters.add(aParameterBuilder.build());

       return new Docket(SWAGGER_2)
               .groupName("public-api")
               .apiInfo(apiInfo())
               .select()
               .paths(postPaths())
               .build()
               .globalOperationParameters(aParameters);
   }

    private Predicate<String> postPaths() {
        return or(
                regex("/api/*.*")
        );
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("REST API")
                .description("API reference for developers")
                .termsOfServiceUrl("http://hantsy.blogspot.com")
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
                .version("2.0")
                .build();
    }
}
