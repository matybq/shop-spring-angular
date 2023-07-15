package com.matybq.balbi.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductRequest {

  private String name;
  private String description;
  private String img;
  private double price;

}
