package com.matybq.balbi.productservice.service;

import com.matybq.balbi.productservice.dto.ProductRequest;
import com.matybq.balbi.productservice.model.Product;
import com.matybq.balbi.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }

    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(String.valueOf(id));
    }

    public void updateProduct(Long id, ProductRequest productRequest) {
        Product product = getProductById(id)
                .orElseThrow(() -> new NoSuchElementException("Product" + id + "NOT FOUND"));
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setDescription(productRequest.getDescription());
        product.setImg(productRequest.getImg());
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(String.valueOf(id));
    }
}
