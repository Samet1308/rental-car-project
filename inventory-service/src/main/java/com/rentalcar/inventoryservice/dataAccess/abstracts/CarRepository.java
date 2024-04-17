package com.rentalcar.inventoryservice.dataAccess.abstracts;

import com.rentalcar.inventoryservice.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Integer> {
}
