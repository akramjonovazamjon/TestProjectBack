package com.example.projecttest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.lifecycle.Startables;


@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public abstract class CommonIntegrationTest {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public ObjectMapper objectMapper;
    @Container
    private static PostgreSQLContainer<?> postgreSQLContainer;

    static {
        postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest")
                .withEnv("MAX_HEAP_SIZE", "256M")
                .withEnv("HEAP_NEWSIZE", "128M")
                .withReuse(true);

        Startables.deepStart(postgreSQLContainer).join();
    }

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @AfterEach
    public void execute() {
        jdbcTemplate.execute("TRUNCATE TABLE organizations RESTART IDENTITY CASCADE ");
        jdbcTemplate.execute("TRUNCATE TABLE positions RESTART IDENTITY CASCADE ");
        jdbcTemplate.execute("TRUNCATE TABLE employee_items RESTART IDENTITY CASCADE ");
        jdbcTemplate.execute("TRUNCATE TABLE employees RESTART IDENTITY CASCADE ");
        jdbcTemplate.execute("TRUNCATE TABLE users RESTART IDENTITY CASCADE ");
    }
}
