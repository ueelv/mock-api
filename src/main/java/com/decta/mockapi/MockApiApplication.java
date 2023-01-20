package com.decta.mockapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("file:${MOCK_API_PROPERTIES}")
public class MockApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MockApiApplication.class, args);
    }

}
