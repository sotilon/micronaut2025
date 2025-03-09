package com.sotil.micronaut.demo.integration;

import com.sotil.micronaut.demo.dto.ProductDTO;
//import io.github.resilience4j.micronaut.annotation.CircuitBreaker;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

import java.util.List;


@Client(id = "${external.microservices.product_service.name}")
public interface ProductClient {

    @Get("/api/v1/product/list")
    List<ProductDTO> listAll();


    @Get("/api/v1/product/{productId}")
    ProductDTO findById(Long productId);

}
