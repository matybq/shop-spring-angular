package com.matybq.balbi.inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.matybq.balbi.inventoryservice.model.Inventory;
import com.matybq.balbi.inventoryservice.repository.InventoryRepository;

@SpringBootApplication
public class InventoryServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(InventoryServiceApplication.class, args);
  }

  @Bean
  public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
    return args -> {
      Inventory inventory = new Inventory();
      inventory.setSkuCode("buzo_negro");
      inventory.setCant(100);

      Inventory inventory1 = new Inventory();
      inventory1.setSkuCode("buzo_rojo");
      inventory1.setCant(0);

      inventoryRepository.save(inventory);
      inventoryRepository.save(inventory1);
    };
  }

}
