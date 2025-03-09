package com.sotil.micronaut.demo.service;

import com.sotil.micronaut.demo.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> listAll();

    Optional<Product> findById(Long id) ;

    Product save(Product product);

    void delete(Long id) ;

}
