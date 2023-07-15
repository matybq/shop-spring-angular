package com.matybq.balbi.orderservice.controller;

import com.matybq.balbi.orderservice.model.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.matybq.balbi.orderservice.dto.OrderRequest;
import com.matybq.balbi.orderservice.service.OrderService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public String placeOrder(@RequestBody OrderRequest orderRequest) {
    orderService.placeOrder(orderRequest);
    return "order placed succesfully";
  }

  @GetMapping
  public List<Order> getAllOrders() {
    return orderService.getAllOrders();
  }

  @GetMapping("/{id}")
  public Order getOrderById(Long id) {
    return orderService.getOrderById(id);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateOrder(Long id, @RequestBody OrderRequest orderRequest) {
    orderService.updateOrder(id, orderRequest);
    return ResponseEntity.ok().body("ORDER NUMBER: " + orderRequest.getNumber() + " ACTUALIZED");
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteOrder(Long id) {
    orderService.deleteOrder(id);
    return ResponseEntity.ok().body("ORDER ID: " + id + " DELETED");
  }



}
