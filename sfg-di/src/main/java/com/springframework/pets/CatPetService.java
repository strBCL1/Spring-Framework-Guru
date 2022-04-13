package com.springframework.pets;

import org.springframework.stereotype.Component;

public class CatPetService implements PetService {
    @Override
    public String doingService() {
        return "Doing Services for Cats!";
    }
}
