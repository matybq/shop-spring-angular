package com.matybq.balbi.productservice.dto;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductResponse {

  @Id
  private String id;
  private String name;
  private String description;
  private String img;
  private double price;

}
