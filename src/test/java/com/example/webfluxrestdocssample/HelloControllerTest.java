package com.example.webfluxrestdocssample;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.ApplicationContext;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;

@WebFluxTest(HelloController.class)
@ExtendWith(RestDocumentationExtension.class)
class HelloControllerTest {

    @Autowired
    private WebTestClient webTestClient;


    @Autowired
    private ApplicationContext context;

    @BeforeEach
    void setUp(RestDocumentationContextProvider restDocumentation) {
        webTestClient = WebTestClient.bindToApplicationContext(context)
                .configureClient()
                .filter(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    @DisplayName("sample")
    void sample() {
        this.webTestClient.get().uri("/hello").exchange()
                .expectStatus().isOk().expectBody()
                .consumeWith(document("sample"));
    }

    @Test
    @DisplayName("header-sample")
    void headerSample() {
        this.webTestClient.get().uri("/hello").header("name", "name-sample")
                .exchange()
                .expectStatus().isOk().expectBody()
                .consumeWith(document("header-sample"));
    }

    @Test
    @DisplayName("post-sample")
    void postSample() {
        this.webTestClient.post().uri("/hello").bodyValue(new HelloRequest("name", "message"))
                .exchange()
                .expectStatus().isCreated().expectBody()
                .consumeWith(document("post-sample"));
    }


}
