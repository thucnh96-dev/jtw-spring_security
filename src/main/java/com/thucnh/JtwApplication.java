package com.thucnh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {"com.thucnh"})
@EntityScan(basePackages = {"com.thucnh"})
@ComponentScan(basePackages = {"com.thucnh"})
public class JtwApplication {

    public static void main(String[] args) {
        SpringApplication.run(JtwApplication.class, args);
    }

}
