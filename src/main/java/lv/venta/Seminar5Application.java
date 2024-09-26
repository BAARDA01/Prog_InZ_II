package lv.venta;

import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lv.venta.model.Product;
import lv.venta.repo.IProductRepo;

@SpringBootApplication
public class Seminar5Application {

	public static void main(String[] args) {
		SpringApplication.run(Seminar5Application.class, args);
	}
	@Bean
	public CommandLineRunner testDatabse(IProductRepo productRepo) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				Product milk = new Product("Milk", "is white", 0.89f, 1);
				Product banana = new Product("Banana", "is vegetable", 0.5f, 3);
				Product carrot = new Product("Carrot", "does not improve vision", 0.33f, 5);
				productRepo.save(milk);
				productRepo.save(banana);
				productRepo.save(carrot);
				
				System.out.println("How many: "+ productRepo.count());
				System.out.println("All products: " + productRepo.findAll());
				System.out.println("Find by id " + productRepo.findById(1));
				
			}
		};
	}
}
