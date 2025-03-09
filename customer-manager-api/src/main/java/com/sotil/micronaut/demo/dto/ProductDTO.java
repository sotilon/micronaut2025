package com.sotil.micronaut.demo.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record ProductDTO(
        Long id,
        //CustomerDTO customer,
        String name,
        String code,
        String description
) {

}
