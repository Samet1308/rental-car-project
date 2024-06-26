package com.rentalcar.inventoryservice.business.requests.carRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateCarRequest {

    @NotNull
    private int modelId;

    @NotNull
    @NotBlank
    private  String plate;

    @NotNull
    private double dailyPrice;

    @NotNull
    private int modelYear;

    @NotNull
    private Boolean stateId;

    private String imageUrl;
}
