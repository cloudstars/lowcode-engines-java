package io.github.cloudstars.lowcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ObjectEditorTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ObjectEditorTestApplication.class, args);
    }

}