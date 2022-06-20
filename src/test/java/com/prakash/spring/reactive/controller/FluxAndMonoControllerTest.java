package com.prakash.spring.reactive.controller;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureWebTestClient
public class FluxAndMonoControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("FluxTest1")
    public void returnFluxTest1() {
        Flux<Integer> integerFlux = webTestClient.get().uri("/flux")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Integer.class)
                .getResponseBody();

        StepVerifier.create(integerFlux)
                .expectSubscription()
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectNext(4)
                .verifyComplete();

    }

    @Test
    @DisplayName("FluxTest2")
    public void returnFluxTest2() {
        webTestClient.get().uri("/flux")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBodyList(Integer.class)
                .hasSize(4);

    }

    @Test
    @DisplayName("FluxTest3")
    public void returnFluxTest3() {

        List<Integer> expectedIntegerList = Arrays.asList(1, 2, 3, 4);

        EntityExchangeResult<List<Integer>> entityExchangeResult =
                webTestClient.get().uri("/flux")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .exchange()
                        .expectStatus().isOk()
                        .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                        .expectBodyList(Integer.class)
                        .returnResult();

        assertEquals(expectedIntegerList, entityExchangeResult.getResponseBody());
    }
    @Test
    @DisplayName("FluxTest4")
    public void returnFluxTest4(){

        List<Integer> expectedIntegerList = Arrays.asList(1,2,3,4);

        webTestClient
                .get().uri("/flux")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Integer.class)
                .consumeWith((response      ) -> {
                    assertEquals(expectedIntegerList, response.getResponseBody());
                });

    }

}
