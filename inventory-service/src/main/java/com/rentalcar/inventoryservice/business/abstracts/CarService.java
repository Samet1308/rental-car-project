package com.rentalcar.inventoryservice.business.abstracts;

import com.rentalcar.inventoryservice.business.dtos.CarDTO;

import java.io.IOException;
import java.util.List;

public interface CarService {

    List<CarDTO> getAllCarsByModel(Long modelId);

    CarDTO postCar(Long modelId, CarDTO carDTO) throws IOException;

    void deleteCar(Long carId);
    
    CarDTO getCarById(Long carId);

    boolean updateCar(Long carId, CarDTO carDTO) throws IOException;

//    boolean bookCar(BookCarDTO bookCarDTO);
}
