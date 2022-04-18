package com.example.recipeproject.converters;

import com.example.recipeproject.commands.UnitOfMeasureCommand;
import com.example.recipeproject.domain.UnitOfMeasure;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand source) {
        if (source == null) {
            return null;
        }

        final UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(source.getId());
        unitOfMeasure.setUom(source.getUom());

        return unitOfMeasure;
    }
}
