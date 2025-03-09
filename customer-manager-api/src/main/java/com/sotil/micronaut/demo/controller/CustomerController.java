package com.sotil.micronaut.demo.controller;

import com.sotil.micronaut.demo.dto.CustomerDTO;
import com.sotil.micronaut.demo.service.CustomerService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller("/api/v1/customer")
@ExecuteOn(TaskExecutors.BLOCKING)  // Ejecuta en un hilo bloqueante, error una operación bloqueante (BlockingHttpClient) en un hilo de evento de Netty
public class CustomerController {
    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

    @Inject
    private CustomerService customerService;

    @Get("/list")
    public List<CustomerDTO> listAll() {
        log.info("find all Customers:::");
        return customerService.listAll();
    }

    @Get("/{id}")
    public HttpResponse<CustomerDTO> findById(@PathVariable Long id) {
        log.info("find by id  Customer ::: id :: {}",id);
        return customerService.findById(id)
                .map(HttpResponse::ok)
                .orElse(HttpResponse.notFound());
    }

}
