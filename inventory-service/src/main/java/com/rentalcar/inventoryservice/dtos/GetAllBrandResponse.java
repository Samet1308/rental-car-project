package com.rentalcar.inventoryservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class GetAllBrandResponse {
    private int id;
    private String name;
}
