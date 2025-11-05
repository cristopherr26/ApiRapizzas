package co.edu.uco.rapizzas.initializer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = { "co.edu.uco.rapizzas" })
@SpringBootApplication
public class ApiRapizzasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRapizzasApplication.class, args);
	}

}
