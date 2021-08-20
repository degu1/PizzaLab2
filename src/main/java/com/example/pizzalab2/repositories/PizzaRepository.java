package com.example.pizzalab2.repositories;

import com.example.pizzalab2.entities.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    public Boolean existsByName(String name);
    public Pizza findPizzaByName(String name);
}
