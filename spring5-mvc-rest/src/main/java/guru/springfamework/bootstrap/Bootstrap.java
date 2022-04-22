package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.repositories.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jt on 9/24/17.
 */
@Component
public class Bootstrap implements CommandLineRunner{

    private final CategoryRepository categoryRepository;

    public Bootstrap(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<String> categoriesNamesList = List.of("Fruits", "Dried", "Fresh", "Exotic", "Nuts");

        for (String categoryName : categoriesNamesList) {
            Category category = new Category();
            category.setName(categoryName);
            categoryRepository.save(category);
        }

        System.out.println("Amount of added categories: " + categoryRepository.count());
    }
}
