package com.rentalcar.inventoryservice.api.controller;

import com.rentalcar.inventoryservice.business.abstracts.BrandService;
import com.rentalcar.inventoryservice.business.dtos.GetAllBrandResponse;
import com.rentalcar.inventoryservice.business.requests.brandRequest.CreateBrandRequest;
import com.rentalcar.inventoryservice.business.requests.brandRequest.UpdateBrandRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@AllArgsConstructor
public class BrandsController {

    private BrandService brandService;

    @GetMapping()
    public List<GetAllBrandResponse> getAll() {
        return this.brandService.getAll();
    }

    @PostMapping
    @ResponseStatus(code= HttpStatus.CREATED)
    public void add(@RequestBody @Valid CreateBrandRequest createBrandRequest){
        this.brandService.add(createBrandRequest);
    }

    @PutMapping()
    public void update(@RequestBody UpdateBrandRequest updateBrandRequest){
        this.brandService.update(updateBrandRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        this.brandService.delete(id);
    }
}
