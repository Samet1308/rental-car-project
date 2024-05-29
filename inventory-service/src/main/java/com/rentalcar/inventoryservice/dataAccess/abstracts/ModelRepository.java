package com.rentalcar.inventoryservice.dataAccess.abstracts;

import com.rentalcar.inventoryservice.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ModelRepository extends JpaRepository<Model,Long > {
    List<Model> findAllByBrandId(Long brandId);

    List<Model> findAllByBrandIdAndNameContaining(Long brandId, String title);
}
