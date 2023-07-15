package com.matybq.balbi.productservice.repository;

import com.matybq.balbi.productservice.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, String>, CrudRepository<Product, String> {

}
