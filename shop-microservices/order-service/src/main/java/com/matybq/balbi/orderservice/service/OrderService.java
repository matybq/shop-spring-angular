package com.matybq.balbi.orderservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import com.matybq.balbi.orderservice.dto.InventoryResponse;
import com.matybq.balbi.orderservice.orderConstants.Status;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.matybq.balbi.orderservice.dto.OrderLineItemsDto;
import com.matybq.balbi.orderservice.dto.OrderRequest;
import com.matybq.balbi.orderservice.model.Order;
import com.matybq.balbi.orderservice.model.OrderLineItems;
import com.matybq.balbi.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor // para incializar el repositorio
@Transactional
public class OrderService {

  // inyecto el repositorio
  private final OrderRepository orderRepository;
  //inyecto webclient para sync comm
  private final WebClient.Builder webClientBuilder;

  public void placeOrder(OrderRequest orderRequest) {
    Order order = new Order();
    order.setStatus(Status.PENDIENTE);
    order.setNumber(UUID.randomUUID().toString());

    List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsList()
        .stream()
        .map(this::mapToDto)
        .toList();
    order.setOrderLineItemsList(orderLineItems);

    List<String> skuCodes = order.getOrderLineItemsList().stream()
            .map(OrderLineItems::getSkuCode)
            .toList();

    // call inventory & place order if product is in stock
    InventoryResponse[] inventoryResponses = webClientBuilder.build().get()
            .uri("http://INVENTORY-SERVICE/api/inventory",
                    uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
            .retrieve()
            .bodyToMono(InventoryResponse[].class)
            .block();

    boolean allProductsInStock = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInStock);

    if (allProductsInStock) {
      orderRepository.save(order);
    }else {
      throw  new IllegalArgumentException("Product out of stock!");
    }

  }

  public List<Order> getAllOrders() {
    return orderRepository.findAll();
  }

  public Order getOrderById(Long id) {
    return orderRepository.getById(String.valueOf(id));
  }

  public void updateOrder(Long id, OrderRequest orderRequest) {
    Order order = orderRepository.getById(String.valueOf(id));
    order.setStatus(orderRequest.getStatus());
    order.setNumber(orderRequest.getNumber());
    order.setOrderLineItemsList(orderRequest.getOrderLineItemsList());
    orderRepository.save(order);
  }

  public void deleteOrder(Long id) {
    orderRepository.delete(orderRepository.getById(String.valueOf(id)));
  }

  public OrderLineItems mapToDto(OrderLineItems orderLineItemsDto) {
    OrderLineItems orderLineItems = new OrderLineItems();
    orderLineItems.setPrice(orderLineItemsDto.getPrice());
    orderLineItems.setCant(orderLineItemsDto.getCant());
    orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());

    return orderLineItems;
  }



}
