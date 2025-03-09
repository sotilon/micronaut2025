package com.sotil.micronaut.demo.controller;

import com.sotil.micronaut.demo.model.Product;
import com.sotil.micronaut.demo.service.ProductService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller("/api/v1/admin/product")
public class ProductAdminController {
    private static final Logger log = LoggerFactory.getLogger(ProductAdminController.class);

    @Inject
    private ProductService productService;

    @Post
    public HttpResponse<Product> create(@Body Product product) {
        log.info("Create new Product  ::: {}",product);
        return HttpResponse.created(productService.save(product));
    }

    @Delete("/{id}")
    public HttpResponse<Void> delete(@PathVariable Long id) {
        log.info(" process delete product with id :: {}",id);
        productService.delete(id);
        return HttpResponse.noContent();
    }

}
