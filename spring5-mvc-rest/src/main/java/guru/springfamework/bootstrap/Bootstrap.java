package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jt on 9/24/17.
 */
@Component
public class Bootstrap implements CommandLineRunner{

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<String> categoriesNamesList = List.of("Fruits", "Dried", "Fresh", "Exotic", "Nuts");

        for (String categoryName : categoriesNamesList) {
            Category category = new Category();
            category.setName(categoryName);
            categoryRepository.save(category);
        }

        List<String> customersFullNames = List.of("Anna Black", "Papa Roach", "Hollywood Undead");

        for (String customerFullName : customersFullNames) {
            Customer customer = new Customer();
            String[] fullName = customerFullName.split(" ");

            customer.setFirstname(fullName[0]);
            customer.setLastname(fullName[1]);
            customerRepository.save(customer);
        }

        System.out.println("Amount of added categories: " + categoryRepository.count());
        System.out.println("Amount of added customers: " + customerRepository.count());
    }
}
