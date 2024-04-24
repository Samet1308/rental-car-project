package com.rentalcar.inventoryservice.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllModelResponse {
    private int id;
    private String name;
    private String brandName;
}
