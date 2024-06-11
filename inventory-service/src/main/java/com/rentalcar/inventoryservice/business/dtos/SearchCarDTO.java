package com.rentalcar.inventoryservice.business.dtos;

import com.rentalcar.inventoryservice.entities.Brand;
import com.rentalcar.inventoryservice.entities.Model;
import lombok.Data;

@Data
public class SearchCarDTO {

    private String brandName;

    private String modelName;

    private Long modelYear;

    private String type;

    private String transmission;

    private String color;
}
