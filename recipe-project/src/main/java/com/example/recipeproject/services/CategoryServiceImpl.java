package com.example.recipeproject.services;

import com.example.recipeproject.converters.CategoryCommandToCategory;
import com.example.recipeproject.converters.CategoryToCategoryCommand;
import com.example.recipeproject.domain.Category;
import com.example.recipeproject.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryToCategoryCommand categoryToCategoryCommand;
    private final CategoryCommandToCategory categoryCommandToCategory;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryToCategoryCommand categoryToCategoryCommand,
                               CategoryCommandToCategory categoryCommandToCategory) {
        this.categoryRepository = categoryRepository;
        this.categoryToCategoryCommand = categoryToCategoryCommand;
        this.categoryCommandToCategory = categoryCommandToCategory;
    }

    @Override
    public Set<Category> getCategories() {
        Set<Category> categorySet = new HashSet<>();
        categoryRepository.findAll().iterator().forEachRemaining(categorySet::add);

        return categorySet;
    }
}
