package com.sotil.micronaut.demo.service;

import com.sotil.micronaut.demo.dto.CustomerDTO;
import com.sotil.micronaut.demo.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<CustomerDTO> listAll();

    Optional<CustomerDTO> findById(Long id) ;

    CustomerDTO save(CustomerDTO customerDTO);

    void delete(Long id) ;

}
