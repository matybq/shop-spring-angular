package com.matybq.balbi.orderservice.dto;

import java.util.List;

import com.matybq.balbi.orderservice.model.OrderLineItems;
import com.matybq.balbi.orderservice.orderConstants.Status;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
  private Long id;
  private String number;
  @OneToMany(cascade = CascadeType.ALL)
  private Status status;
  private List<OrderLineItems> orderLineItemsList;
}
