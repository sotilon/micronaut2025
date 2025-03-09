package com.sotil.micronaut.demo.integration;

import com.sotil.micronaut.demo.dto.ProductDTO;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
//import io.micronaut.retry.annotation.Fallback;

import java.util.List;

//@Fallback
@Client(id = "${external.microservices.product_backup_service.name}")
public interface ProductBackupClient {

    @Get("/api/v1/product/list")
    List<ProductDTO> listAll();

    @Get("/api/v1/product/{productId}")
    ProductDTO fallback(Long productId);

}
