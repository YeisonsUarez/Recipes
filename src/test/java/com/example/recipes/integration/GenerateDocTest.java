package com.example.recipes.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class GenerateDocTest extends IntegrationTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void generateSwaggerDocumentation() throws IOException {
        ResponseEntity<String> responseEntity = this.testRestTemplate.getForEntity("/v3/api-docs", String.class);
        assertNotNull(responseEntity.getBody());
        Path outputDir = Paths.get("docs");
        Files.createDirectories(outputDir);
        Files.writeString(outputDir.resolve("swagger.yaml"), responseEntity.getBody());
        //Se usa swagger
    }
}
