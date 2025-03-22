package io.github.cloudstars.lowcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ObjectCommonsTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ObjectCommonsTestApplication.class, args);
    }

}