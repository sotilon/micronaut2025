package com.sotil.micronaut.demo.controller;

import com.sotil.micronaut.demo.model.Product;
import com.sotil.micronaut.demo.service.ProductService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller("/api/v1/product")
public class ProductController {
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    @Inject
    private ProductService productService;

    @Get("/list")
    public HttpResponse<List<Product>> listAll() {
        log.info("BACKUP:: find all products:::");
        List<Product> lstClientes= productService.listAll();
        if (lstClientes.isEmpty() ){
            return HttpResponse.status(HttpStatus.NO_CONTENT);
        }
        return HttpResponse.status(HttpStatus.OK).body(lstClientes);
    }

    @Get("/{id}")
    public HttpResponse<Product> findById(@PathVariable Long id) {
        log.info("BACKUP:: find by id  product ::: id :: {}",id);
        return productService.findById(id)
                .map(HttpResponse::ok)
                .orElse(HttpResponse.notFound());
    }

}
