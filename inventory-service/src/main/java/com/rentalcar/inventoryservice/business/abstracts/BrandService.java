package com.rentalcar.inventoryservice.business.abstracts;

import com.rentalcar.inventoryservice.business.dtos.BrandDTO;
import com.rentalcar.inventoryservice.business.dtos.GetAllBrandResponse;
import com.rentalcar.inventoryservice.business.requests.brandRequest.CreateBrandRequest;
import com.rentalcar.inventoryservice.business.requests.brandRequest.DeleteBrandRequest;
import com.rentalcar.inventoryservice.business.requests.brandRequest.UpdateBrandRequest;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.List;

public interface BrandService {


    BrandDTO postBrand(BrandDTO brandDTO) throws IOException;


    List<BrandDTO> getAllBrands();

    List<BrandDTO> getAllBrandsByTitle(String title);

}
