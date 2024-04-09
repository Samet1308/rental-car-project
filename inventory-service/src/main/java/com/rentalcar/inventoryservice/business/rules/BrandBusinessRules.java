package com.rentalcar.inventoryservice.business.rules;


import com.rentalcar.inventoryservice.core.utilities.exceptions.BusinessException;
import com.rentalcar.inventoryservice.dataAccess.abstracts.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BrandBusinessRules {
    private BrandRepository brandRepository;
    public void checkIfBrandNameExists(String name){
        if(this.brandRepository.existsByName(name)){
            throw new BusinessException("Brand name already exists");
        }
    }
    public void checkIfBrandIdExists(int brandId){
        if(this.brandRepository.existsById(brandId)){
            throw new BusinessException("Brand id already exist");
        }
    }
}
