package com.example.recipeproject.services;

import com.example.recipeproject.domain.Category;

import java.util.Set;

public interface CategoryService {
    Set<Category> getCategories();
}
