package com.matybq.balbi.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matybq.balbi.inventoryservice.model.Inventory;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findBySkuCodeIn(List<String> skuCode);
}
