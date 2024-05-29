package com.rentalcar.inventoryservice.api.controller;

import com.rentalcar.inventoryservice.business.abstracts.BrandService;
import com.rentalcar.inventoryservice.business.dtos.BrandDTO;
import com.rentalcar.inventoryservice.business.dtos.GetAllBrandResponse;
import com.rentalcar.inventoryservice.business.requests.brandRequest.CreateBrandRequest;
import com.rentalcar.inventoryservice.business.requests.brandRequest.UpdateBrandRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@AllArgsConstructor
public class BrandsController {

    private BrandService brandService;

    @GetMapping("/brands")
    public ResponseEntity<List<BrandDTO>> getAllBrands(){
        List<BrandDTO> brandDtoList = brandService.getAllBrands();
        if(brandDtoList==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(brandDtoList);
    }

    @GetMapping("/brands/{title}")
    public ResponseEntity<List<BrandDTO>> getAllBrandsByTitle(@PathVariable String title){
        List<BrandDTO> brandDtoList = brandService.getAllBrandsByTitle(title);
        if(brandDtoList==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(brandDtoList);
    }

    @PostMapping("/brand")
    @ResponseStatus(code= HttpStatus.CREATED)
    public ResponseEntity<BrandDTO> postBrand(@ModelAttribute BrandDTO brandDTO) throws IOException {
        BrandDTO createdBrandDTO = brandService.postBrand(brandDTO);
        if(createdBrandDTO==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(createdBrandDTO);
    }


}
