package com.rentalcar.inventoryservice.business.requests.modelRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateModelRequest {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    private int brandId;

}
