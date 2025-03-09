package com.sotil.micronaut.demo.service;

import com.sotil.micronaut.demo.model.Product;
import com.sotil.micronaut.demo.repository.ProductRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class ProductServiceImpl implements ProductService{
    @Inject
    private ProductRepository productRepository;


    @Override
    public List<Product> listAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

}
