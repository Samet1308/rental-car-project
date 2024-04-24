package com.rentalcar.inventoryservice.business.requests.modelRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class UpdateModelRequest {

    private int id;
    private String name;
    private int brandId;
}
