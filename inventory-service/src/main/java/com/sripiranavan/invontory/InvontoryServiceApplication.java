package com.sripiranavan.invontory;

import com.sripiranavan.invontory.model.Inventory;
import com.sripiranavan.invontory.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InvontoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvontoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
		return (args) -> {
			Inventory inventory = new Inventory();
			inventory.setSkuCode("iphone_13");
			inventory.setQuantity(10);
			inventoryRepository.save(inventory);

			Inventory inventory2 = new Inventory();
			inventory2.setSkuCode("iphone_11");
			inventory2.setQuantity(10);
			inventoryRepository.save(inventory2);
		};
	}
}
