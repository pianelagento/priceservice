package com.example.priceservice.infrastructure.web;

import com.example.priceservice.PriceserviceApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(classes = PriceserviceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")

public class PriceControllerIT {

    @Autowired
    WebTestClient client;

    String base = "/api/v1/prices?brandId=1&productId=35455&applicationDate=";

    @BeforeEach
    void setUp() {}

    @Test
    void test1_14th_10am() {
        client.get()
                .uri(base + "2020-06-14T10:00:00")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.priceList").isEqualTo(1)
                .jsonPath("$.price").isEqualTo(35.50);
    }

    @Test
    void test2_14th_16pm() {
        client.get()
                .uri(base + "2020-06-14T16:00:00")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.priceList").isEqualTo(2)
                .jsonPath("$.price").isEqualTo(25.45);
    }

    @Test
    void test3_14th_21pm() {
        client.get()
                .uri(base + "2020-06-14T21:00:00")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.priceList").isEqualTo(1)
                .jsonPath("$.price").isEqualTo(35.50);
    }

    @Test
    void test4_15th_10am() {
        client.get()
                .uri(base + "2020-06-15T10:00:00")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.priceList").isEqualTo(3)
                .jsonPath("$.price").isEqualTo(30.50);
    }

    @Test
    void test5_16th_21pm() {
        client.get()
                .uri(base + "2020-06-16T21:00:00")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.priceList").isEqualTo(4)
                .jsonPath("$.price").isEqualTo(38.95);
    }
}
