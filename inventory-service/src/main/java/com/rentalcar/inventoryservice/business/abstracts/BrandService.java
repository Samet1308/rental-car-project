package com.rentalcar.inventoryservice.business.abstracts;

import com.rentalcar.inventoryservice.dtos.GetAllBrandResponse;

import java.util.List;

public interface BrandService {
    void add();
    void update();
    void delete();

    List<GetAllBrandResponse> getAll();
}
