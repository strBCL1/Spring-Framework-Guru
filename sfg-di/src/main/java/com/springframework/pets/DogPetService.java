package com.springframework.pets;

import org.springframework.stereotype.Component;

@Component
public class DogPetService implements PetService {
    @Override
    public String doingService() {
        return "Doing Services for Dogs!";
    }
}
