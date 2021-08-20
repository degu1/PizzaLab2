package com.example.pizzalab2;

import com.example.pizzalab2.entities.Pizza;
import com.example.pizzalab2.repositories.PizzaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PizzaLab2Application {

    public static void main(String[] args) {
        SpringApplication.run(PizzaLab2Application.class, args);
    }

    @Bean
    public CommandLineRunner init(PizzaRepository pizzaRepository){
        return (args) -> {
            if(pizzaRepository.count() == 0){
            pizzaRepository.save(new Pizza(0L,"Hawaii",80,"Tomatsås, Ost, Skinka, Annanas"));
            }
        };
    }
}
// docker run --name mysqltest -e MYSQL_ROOT_PASSWORD=root -e 'MYSQL_ROOT_HOST=%' -e MYSQL_DATABASE=test -e MYSQL_USER=user -e MYSQL_USER=user -e MYSQL_PASSWORD=password -p 3307:3306 mysql:latest

// docker pull consul
// docker run -d -p 8500:8500 -p 8600:8600/udp --name=badger consul