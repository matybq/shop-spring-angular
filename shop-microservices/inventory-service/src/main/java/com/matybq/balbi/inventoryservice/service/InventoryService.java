package com.matybq.balbi.inventoryservice.service;

import com.matybq.balbi.inventoryservice.dto.InventoryResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.matybq.balbi.inventoryservice.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor // para incializar el repositorio
public class InventoryService {
  
  // inyecto el repositorio
  private final InventoryRepository inventoryRepository;

  @Transactional(readOnly = true)
  public List<InventoryResponse> isInStock(List<String> skuCode) {
    return inventoryRepository.findBySkuCodeIn(skuCode).stream()
            .map(inventory ->
                InventoryResponse.builder()
                        .skuCode(inventory.getSkuCode())
                        .isInStock(inventory.getCant() > 0)
                        .build()
            ).toList();
  }
}
