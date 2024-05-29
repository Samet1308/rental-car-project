package com.rentalcar.inventoryservice.dataAccess.abstracts;

import com.rentalcar.inventoryservice.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByModelId(Long modelId);

}
