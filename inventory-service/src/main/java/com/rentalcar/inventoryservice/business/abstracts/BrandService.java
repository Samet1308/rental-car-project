package com.rentalcar.inventoryservice.business.abstracts;

import com.rentalcar.inventoryservice.business.dtos.GetAllBrandResponse;
import com.rentalcar.inventoryservice.business.requests.brandRequest.CreateBrandRequest;
import com.rentalcar.inventoryservice.business.requests.brandRequest.DeleteBrandRequest;
import com.rentalcar.inventoryservice.business.requests.brandRequest.UpdateBrandRequest;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface BrandService {


    void add(CreateBrandRequest createBrandRequest);

    void update(UpdateBrandRequest updateBrandRequest);

    List<GetAllBrandResponse> getAll();

    void delete(int id);
}
