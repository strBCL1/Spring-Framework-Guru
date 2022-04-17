package com.example.recipeproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ingredientName;
    private Double amount;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure uom;

    @ManyToOne
    private Recipe recipe;

    public Ingredient() {}

    public Ingredient(String ingredientName, Double amount, UnitOfMeasure uom) {
        this.ingredientName = ingredientName;
        this.amount = amount;
        this.uom = uom;
    }

}
