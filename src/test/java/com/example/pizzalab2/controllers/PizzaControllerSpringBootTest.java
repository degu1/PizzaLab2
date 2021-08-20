package com.example.pizzalab2.controllers;

import com.example.pizzalab2.repositories.PizzaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PizzaControllerSpringBootTest {

    @Autowired
    PizzaController pizzaController;

    @Test
    void getAllPizzas() {
        assertThat(pizzaController.getAllPizzas().size()).isEqualTo(1);
    }
}