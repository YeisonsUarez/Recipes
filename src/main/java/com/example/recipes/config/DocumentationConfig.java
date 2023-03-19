package com.example.recipes.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DocumentationConfig {

    private static final String LOCAL_HOST = "http://localhost:8080";

    @Bean
    public OpenAPI api(
            @Value("${info.tittle}") String tittle,
            @Value("${info.description}") String description,
            @Value("${info.version}") String version
    ) {
        Info info = new Info().title(tittle).description(description).version(version);
        Server server = new Server().url(LOCAL_HOST);
        return new OpenAPI().info(info).addServersItem(server);
    }

}