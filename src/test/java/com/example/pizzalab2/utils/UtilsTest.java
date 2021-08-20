package com.example.pizzalab2.utils;

import com.example.pizzalab2.entities.Pizza;
import static com.example.pizzalab2.utils.Utils.ignoreProperties;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void ignorePropertiesTest() {
        Pizza pizza = new Pizza();
        pizza.setId(1L);
        pizza.setName("Hawaii");

        String[] ignore = ignoreProperties(pizza);

        int actual = ignore.length;
        int expected = 2;
        assertEquals(expected, actual);
    }
}