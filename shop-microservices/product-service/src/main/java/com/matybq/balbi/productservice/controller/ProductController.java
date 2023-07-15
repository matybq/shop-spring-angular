package com.matybq.balbi.productservice.controller;

import java.util.NoSuchElementException;
import java.util.Optional;
import com.matybq.balbi.productservice.dto.ProductRequest;
import com.matybq.balbi.productservice.model.Product;
import com.matybq.balbi.productservice.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
        return ResponseEntity.ok().body("PRODUCT: " + productRequest.getName() + " CREATED");
    }

    @GetMapping
    public Page<Product> getProductsList(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        Pageable paging = PageRequest.of(page, size);
        return productService.getAllProducts(paging);
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        productService.updateProduct(id, productRequest);
        return ResponseEntity.ok().body("PRODUCT: " + productRequest.getName() + " ACTUALIZED");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        return productService.getProductById(id).map(p -> {
            productService.deleteProduct(id);
            return ResponseEntity.ok().body("PRODUCT "+id+" DELETED");
        }).orElseThrow(() -> new NoSuchElementException("PRODUCT: "+id+"NOT FOUND"));
    }

}
