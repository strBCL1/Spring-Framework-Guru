package com.example.recipeproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String notes;

    @OneToOne
    private Recipe recipe;

    public Notes() {}

    public Notes(String notes) {
        this.notes = notes;
    }

}
