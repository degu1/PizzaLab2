package com.example.pizzalab2.controllers;

import com.example.pizzalab2.entities.Pizza;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PizzaEndToEndTest {

    @LocalServerPort
    private int port;



    @Test
    void getPizzasReturnsListOfPizza() throws JsonProcessingException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:"+port+"/pizzas"))
                .build();
        HttpResponse<String> respons = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .join();

        ObjectMapper mapper =new ObjectMapper();
        Pizza[] actual =mapper.readValue(respons.body(), Pizza[].class);
        Pizza expected = new Pizza(0L,"Hawaii",80,"Tomatsås, Ost, Skinka, Annanas");

        assertThat(respons.statusCode()).isEqualTo(200);
        assertThat(actual[0].getName()).isEqualTo(expected.getName());
    }

    @Test
    void putPizzaAndPizzaInDB() throws IOException {
        HttpClient client = HttpClient.newHttpClient();
        Pizza pizza =new Pizza(0L,"Kebab",80,"Tomatsås, Ost, Lök, Kebab");
        ObjectMapper mapper = new ObjectMapper();
        String requestBody = mapper.writeValueAsString(pizza);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:"+port+"/pizzas"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> respons = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .join();



        assertThat(respons.statusCode()).isEqualTo(200);

    }
}