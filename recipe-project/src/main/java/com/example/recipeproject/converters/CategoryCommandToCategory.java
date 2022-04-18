package com.example.recipeproject.converters;

import com.example.recipeproject.commands.CategoryCommand;
import com.example.recipeproject.domain.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

    @Override
    public Category convert(CategoryCommand source) {
        if (source == null) {
            return null;
        }

        final Category category = new Category();
        category.setId(source.getId());
        category.setCategoryName(source.getCategoryName());

        return category;
    }
}
