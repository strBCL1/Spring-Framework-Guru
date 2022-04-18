package com.example.recipeproject.converters;

import com.example.recipeproject.commands.CategoryCommand;
import com.example.recipeproject.domain.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    @Override
    public CategoryCommand convert(Category source) {
        if (source == null) {
            return null;
        }

        final CategoryCommand categoryCommand = new CategoryCommand();

        categoryCommand.setId(source.getId());
        categoryCommand.setCategoryName(source.getCategoryName());

        return categoryCommand;
    }
}
