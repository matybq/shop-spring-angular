package com.matybq.balbi.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matybq.balbi.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, String> {

}
