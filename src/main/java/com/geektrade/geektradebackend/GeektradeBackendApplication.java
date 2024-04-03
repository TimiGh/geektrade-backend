package com.geektrade.geektradebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.geektrade"})
@EnableJpaRepositories(basePackages = {"com.geektrade"})
public class GeektradeBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeektradeBackendApplication.class, args);
    }

}
