package com.rentalcar.inventoryservice.business.concretes;

import com.rentalcar.inventoryservice.business.abstracts.BrandService;
import com.rentalcar.inventoryservice.business.rules.BrandBusinessRules;
import com.rentalcar.inventoryservice.core.utilities.mappers.ModelMapperService;
import com.rentalcar.inventoryservice.dataAccess.abstracts.BrandRepository;
import com.rentalcar.inventoryservice.business.dtos.GetAllBrandResponse;
import com.rentalcar.inventoryservice.entities.Brand;
import com.rentalcar.inventoryservice.business.requests.brandRequest.CreateBrandRequest;
import com.rentalcar.inventoryservice.business.requests.brandRequest.UpdateBrandRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BrandServiceImpl implements BrandService {

    private BrandRepository brandRepository;
    private ModelMapperService modelMapperService;
    private BrandBusinessRules brandBusinessRules;

    @Override
    public List<GetAllBrandResponse> getAll(){

        List<Brand> brands = this.brandRepository.findAll();

        List<GetAllBrandResponse> brandResponses = brands.stream()
                .map(brand -> this.modelMapperService.forResponse()
                        .map(brand,GetAllBrandResponse.class)).collect(Collectors.toList());
        return brandResponses;
    }


    @Override
    public void add(CreateBrandRequest createBrandRequest){
        this.brandBusinessRules.checkIfBrandNameExists(createBrandRequest.getName());
        Brand brand = this.modelMapperService.forRequest().map(createBrandRequest,Brand.class);
        this.brandRepository.save(brand);
    }

    @Override
    public void update(UpdateBrandRequest updateBrandRequest) {

    Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest,Brand.class);
    this.brandRepository.save(brand);

    }

    @Override
    public void delete(@PathVariable int id) {
        this.brandRepository.deleteById(id);
    }
}
