package com.sotil.micronaut.demo.repository;

import com.sotil.micronaut.demo.model.Customer;
import com.sotil.micronaut.demo.model.Product;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {


}
