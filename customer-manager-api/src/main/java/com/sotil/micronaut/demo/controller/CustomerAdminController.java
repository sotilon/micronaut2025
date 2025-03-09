package com.sotil.micronaut.demo.controller;

import com.sotil.micronaut.demo.dto.CustomerDTO;
import com.sotil.micronaut.demo.model.Customer;
import com.sotil.micronaut.demo.service.CustomerService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/api/v1/admin/customer")
public class CustomerAdminController {
    private static final Logger log = LoggerFactory.getLogger(CustomerAdminController.class);

    @Inject
    private CustomerService customerService;

    @Post
    public HttpResponse<CustomerDTO> create(@Body CustomerDTO customerDTO) {
        log.info("Create new Customer  ::: {}",customerDTO);
        return HttpResponse.created(customerService.save(customerDTO));
    }

    @Delete("/{id}")
    public HttpResponse<Void> delete(@PathVariable Long id) {
        log.info(" process delete Customer with id :: {}",id);
        customerService.delete(id);
        return HttpResponse.noContent();
    }

}
