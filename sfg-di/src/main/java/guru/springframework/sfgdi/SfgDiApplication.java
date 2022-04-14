package guru.springframework.sfgdi;

import com.springframework.pets.CatPetService;
import com.springframework.pets.DogPetService;
import guru.springframework.sfgdi.controllers.*;
import guru.springframework.sfgdi.datasource.FakeDataSource;
import guru.springframework.sfgdi.services.GreetingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = { "guru.springframework.sfgdi", "com.springframework.pets"})
public class SfgDiApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SfgDiApplication.class, args);
		MyController myController = (MyController) ctx.getBean("myController");

		I18nController i18nController = ctx.getBean("i18nController", I18nController.class);
		System.out.println(i18nController.sayHello());

		System.out.println("------- PRIMARY bean");

		System.out.println(myController.sayHello());

		System.out.println("------- Property");

		PropertyInjectedController propertyInjectedController = ctx.getBean("propertyInjectedController",
				PropertyInjectedController.class);

		System.out.println(propertyInjectedController.getGreeting());

		System.out.println("------- Setter");

		SetterInjectedController setterInjectedController = ctx.getBean("setterInjectedController",
				SetterInjectedController.class);

		System.out.println(setterInjectedController.getGreeting());

		System.out.println("------- Constructor");

		ConstructorInjectedController constructorInjectedController = ctx.getBean("constructorInjectedController",
				ConstructorInjectedController.class);

		System.out.println(constructorInjectedController.getGreeting());

		System.out.println("------- PetService");

		DogPetService dogPetService = ctx.getBean("dogPetService", DogPetService.class);

		System.out.println(dogPetService.doingService());

		FakeDataSource fakeDataSource = ctx.getBean(FakeDataSource.class);
		System.out.println(fakeDataSource.getUsername() + " " + fakeDataSource.getPassword() + " " + fakeDataSource.getJdbcURL());
	}
}
