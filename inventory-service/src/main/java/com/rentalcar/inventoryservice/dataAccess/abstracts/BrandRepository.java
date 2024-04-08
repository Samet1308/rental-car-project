package com.rentalcar.inventoryservice.dataAccess.abstracts;

import com.rentalcar.inventoryservice.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Integer> {

}
