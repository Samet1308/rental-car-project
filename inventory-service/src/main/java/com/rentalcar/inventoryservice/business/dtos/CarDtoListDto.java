package com.rentalcar.inventoryservice.business.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CarDtoListDto {
    List<CarDTO> carDTOList;
}
