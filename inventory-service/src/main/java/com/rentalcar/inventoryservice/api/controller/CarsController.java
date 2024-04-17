package com.rentalcar.inventoryservice.api.controller;

import com.rentalcar.inventoryservice.business.abstracts.CarService;
import com.rentalcar.inventoryservice.business.dtos.GetAllCarResponse;
import com.rentalcar.inventoryservice.business.requests.carRequest.CreateCarRequest;
import com.rentalcar.inventoryservice.business.requests.carRequest.UpdateCarRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@AllArgsConstructor
public class CarsController {

    private CarService carService;
    @GetMapping
    public List<GetAllCarResponse> getAll(){
        return this.carService.getAll();
    }
    @PostMapping
    @ResponseStatus(code= HttpStatus.CREATED)
    public void add(CreateCarRequest createCarRequest){
        this.carService.add(createCarRequest);
    }

    @PutMapping
    public void update(UpdateCarRequest updateCarRequest){
        this.carService.update(updateCarRequest);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        this.carService.delete(id);
    }
}
