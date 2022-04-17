package com.example.recipeproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uom;

    public UnitOfMeasure() {}

    public UnitOfMeasure(String uom) {
        this.uom = uom;
    }

}
