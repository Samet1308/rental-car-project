package com.rentalcar.inventoryservice.business.concretes;

import com.rentalcar.inventoryservice.business.abstracts.BrandService;
import com.rentalcar.inventoryservice.business.dtos.BrandDTO;
import com.rentalcar.inventoryservice.business.rules.BrandBusinessRules;
import com.rentalcar.inventoryservice.core.utilities.mappers.ModelMapperService;
import com.rentalcar.inventoryservice.dataAccess.abstracts.BrandRepository;
import com.rentalcar.inventoryservice.entities.Brand;
import com.rentalcar.inventoryservice.business.requests.brandRequest.UpdateBrandRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BrandServiceImpl implements BrandService {

    private BrandRepository brandRepository;
    private ModelMapperService modelMapperService;
    private BrandBusinessRules brandBusinessRules;

    @Override
    public List<BrandDTO> getAllBrands() {
        return brandRepository.findAll().stream().map(Brand::getBrandDto).collect(Collectors.toList());
    }

    @Override
    public List<BrandDTO> getAllBrandsByTitle(String title) {
        return brandRepository.findAllByNameContaining(title).stream().map(Brand::getBrandDto).collect(Collectors.toList());
    }


    @Override
    public BrandDTO postBrand(BrandDTO brandDTO) throws IOException {
        Brand brand = new Brand();
        brand.setName(brandDTO.getName());
        brand.setImage(brandDTO.getImage().getBytes());

        Brand createdBrand = brandRepository.save(brand);
        BrandDTO createdBrandDto = new BrandDTO();
        createdBrandDto.setId(createdBrand.getId());
        return createdBrandDto ;
    }

}
