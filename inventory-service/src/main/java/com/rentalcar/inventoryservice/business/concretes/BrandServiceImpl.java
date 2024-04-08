package com.rentalcar.inventoryservice.business.concretes;

import com.rentalcar.inventoryservice.business.abstracts.BrandService;
import com.rentalcar.inventoryservice.core.utilities.ModelMapperService;
import com.rentalcar.inventoryservice.dataAccess.abstracts.BrandRepository;
import com.rentalcar.inventoryservice.dtos.GetAllBrandResponse;
import com.rentalcar.inventoryservice.entities.Brand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BrandServiceImpl implements BrandService {

    private BrandRepository brandRepository;
    private ModelMapperService modelMapperService;
    @Override
    public List<GetAllBrandResponse> getAll(){

        List<Brand> brands = this.brandRepository.findAll();

        List<GetAllBrandResponse> brandResponses = brands.stream()
                .map(brand -> this.modelMapperService.forResponse()
                        .map(brand,GetAllBrandResponse.class)).collect(Collectors.toList());
        return brandResponses;
    }


    @Override
    public void add(){

    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }
}
