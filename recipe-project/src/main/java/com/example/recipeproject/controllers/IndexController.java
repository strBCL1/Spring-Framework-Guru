package com.example.recipeproject.controllers;

import com.example.recipeproject.domain.Category;
import com.example.recipeproject.domain.UnitOfMeasure;
import com.example.recipeproject.repositories.CategoryRepository;
import com.example.recipeproject.repositories.UnitOfMeasureRepository;
import org.jboss.jandex.Index;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {
    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage() {
        System.out.println("Say something...");

        Optional<Category> categoryOptional = categoryRepository.findByDescription("american");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByUom("ounce");

        categoryOptional.ifPresentOrElse(
                category -> System.out.println(category.getDescription()),
                () -> System.out.println("no such category found!"));

        unitOfMeasureOptional.ifPresentOrElse(
                unitOfMeasure -> System.out.println(unitOfMeasure.getUom()),
                () -> System.out.println("no such uom found!")
        );

        return "index";
    }
}
