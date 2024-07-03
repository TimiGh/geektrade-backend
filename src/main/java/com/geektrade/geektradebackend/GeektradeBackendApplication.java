package com.geektrade.geektradebackend;

import com.geektrade.geektradebackend.config.JacksonConfig;
import com.geektrade.geektradebackend.config.MethodSecurityConfig;
import com.geektrade.geektradebackend.config.SecurityConfig;
import com.geektrade.geektradebackend.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.geektrade"})
@EnableJpaRepositories(basePackages = {"com.geektrade"})
@ComponentScan(basePackages = {"com.geektrade"})
public class GeektradeBackendApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(GeektradeBackendApplication.class, args);
    }

}
