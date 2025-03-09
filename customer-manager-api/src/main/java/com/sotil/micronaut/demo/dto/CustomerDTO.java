package com.sotil.micronaut.demo.dto;

import io.micronaut.serde.annotation.Serdeable;

import java.util.List;

@Serdeable
public record CustomerDTO(
        Long id,
        String accountNumber,
        String address,
        String code,
        String names,
        String phone,
        List<ProductDTO> products
) {


}
