package org.example.hw002;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Hibernate/JPA Homework",
        version = "v1",
        description = "This Spring Boot application provides CRUD operations for managing products using JPA EntityManager. It supports product creation, updating, deletion, retrieval, and pagination.")
)
public class Hw002Application {
    public static void main(String[] args) {
        SpringApplication.run(Hw002Application.class, args);
    }

}
