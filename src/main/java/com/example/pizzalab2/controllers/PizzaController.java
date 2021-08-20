package com.example.pizzalab2.controllers;

import com.example.pizzalab2.repositories.PizzaRepository;
import com.example.pizzalab2.entities.Pizza;

import static com.example.pizzalab2.utils.Utils.*;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class PizzaController {
    private final PizzaRepository pizzaRepository;

    @Value("${spring.cloud.consul.discovery.instance-id}")
    private String id;

    public PizzaController(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }


    @GetMapping("/pizzas")
    List<Pizza> getAllPizzas() {
        System.out.println("All pizzas");
        return pizzaRepository.findAll();
    }

    @GetMapping("/pizzas/test")
    public String test() {
        return id;
    }

    @GetMapping("/pizzas/id/{id}")
    Optional<Pizza> searchById(@PathVariable Long id) {
        if (pizzaRepository.existsById(id)) {
            return pizzaRepository.findById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/pizzas/name/{name}")
    Pizza searchByName(@PathVariable String name) {
        if (pizzaRepository.existsByName(name)) {
            return pizzaRepository.findPizzaByName(name);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/pizzas/search/{searchterm}")
    List<Pizza> searchPizzaByIngrdient(@PathVariable String searchterm){
        List<Pizza> pizzas = pizzaRepository.findAll().stream()
                .filter(pizza -> pizza.getIngridients().contains(searchterm))
                .collect(Collectors.toList());
        if (pizzas.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }else{
            return pizzas;
        }
    }

    @PostMapping("/pizzas")
    ResponseEntity<Pizza> addPizza(@RequestBody Pizza pizza) {
        pizza = pizzaRepository.save(pizza);
        return new ResponseEntity<>(pizza,HttpStatus.CREATED);
    }

    @DeleteMapping("/pizzas")
    void deletePizza(Long id) {
        if (pizzaRepository.existsById(id)) {
            pizzaRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/pizzas")
    ResponseEntity<Pizza> changePizzaPatch(@RequestBody Pizza newPizza) {
        if (pizzaRepository.existsById(newPizza.getId())) {
            Pizza pizza = pizzaRepository.getById(newPizza.getId());
            BeanUtils.copyProperties(newPizza, pizza, ignoreProperties(newPizza));

            final Pizza response = pizzaRepository.save(pizza);
            return ResponseEntity.ok(response);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/pizzas")
    ResponseEntity<Pizza> changePizzaPut(@RequestBody Pizza newPizza) {
        if (pizzaRepository.existsById(newPizza.getId())) {
            final Pizza response = pizzaRepository.save(newPizza);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.ok(pizzaRepository.save(newPizza));
        }
    }


}
