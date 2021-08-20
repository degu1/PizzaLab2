package com.example.pizzalab2.controllers;

import com.example.pizzalab2.entities.Pizza;
import com.example.pizzalab2.repositories.PizzaRepository;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.hibernate.service.spi.InjectService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {PizzaController.class})
class PizzaMVCTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PizzaRepository pizzaRepository;


    @Test
    void getAllPizzasReturnsOnePizza() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/pizzas"))
                .andExpect(status().is(200));

    }
}