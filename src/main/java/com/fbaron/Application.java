package com.fbaron;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(title = "User Management Micro-Service",
                description = "This is a demo Spring Boot project for the creation and querying of users.",
                version = "0.0.1"),
        servers = {@Server(url = "http://localhost:8080/user-management")})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
