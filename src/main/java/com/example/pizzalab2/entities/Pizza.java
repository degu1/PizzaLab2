package com.example.pizzalab2.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pizza {
    @Id @GeneratedValue (strategy = GenerationType.AUTO)
    public Long id;
    public String name;
    public Integer price;
    public String ingridients;
}
