package com.rentalcar.inventoryservice.dataAccess.abstracts;

import com.rentalcar.inventoryservice.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    boolean existsByName(String name);

    boolean existsById(int brandId);

    List<Brand> findAllByNameContaining(String title);
}
