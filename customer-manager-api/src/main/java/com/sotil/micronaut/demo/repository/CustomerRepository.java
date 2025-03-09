package com.sotil.micronaut.demo.repository;

import com.sotil.micronaut.demo.model.Customer;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long>{
}
