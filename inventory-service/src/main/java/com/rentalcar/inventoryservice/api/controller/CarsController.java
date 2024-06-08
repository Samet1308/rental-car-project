package com.rentalcar.inventoryservice.api.controller;

import com.rentalcar.inventoryservice.business.abstracts.CarService;
import com.rentalcar.inventoryservice.business.dtos.*;
import com.rentalcar.inventoryservice.business.requests.carRequest.CreateCarRequest;
import com.rentalcar.inventoryservice.business.requests.carRequest.UpdateCarRequest;
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
public class CarsController {

    private CarService carService;
    @PostMapping("/{modelId}/car")
    @ResponseStatus(code= HttpStatus.CREATED)
    public ResponseEntity<CarDTO> postCar(@PathVariable Long modelId, @ModelAttribute CarDTO carDTO) throws IOException {
        CarDTO createdCarDTO = carService.postCar(modelId, carDTO);
        if(createdCarDTO==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(createdCarDTO);
    }

    @GetMapping("/{modelId}/cars")
    public ResponseEntity<List<CarDTO>> getAllModelsByBrand(@PathVariable Long modelId){
        List<CarDTO> carDtoList = carService.getAllCarsByModel(modelId);
        if(carDtoList==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(carDtoList);
    }


    @GetMapping("/car/{carId}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable Long carId){
        CarDTO carDto = carService.getCarById(carId);
        if(carDto==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(carDto);
    }

    @DeleteMapping("/car/{carId}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long carId){
        carService.deleteCar(carId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/car/{carId}")
    public ResponseEntity<Void> updateCar(@PathVariable Long carId, @ModelAttribute CarDTO carDTO) throws IOException {
        try{
            boolean success = carService.updateCar(carId,carDTO);
            if (success) return ResponseEntity.status(HttpStatus.OK).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch(Exception e ){
              return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }
    @PostMapping("/car/book")
    public ResponseEntity<?> bookCar( @RequestBody BookCarDTO bookCarDTO) {
        boolean success = carService.bookCar(bookCarDTO);
        if (success) return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @GetMapping("/car/bookings/{userId}")
    public ResponseEntity<List<BookCarDTO>> getBookingsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(carService.getBookingsByUserId(userId));
    }

    @GetMapping("/car/bookings")
    public ResponseEntity<List<BookCarDTO>> getBookings() {
        return ResponseEntity.ok(carService.getBookings());
    }

    @GetMapping("/car/booking/{bookingId}/{status}")
    public ResponseEntity<?> changeBookingStatus(@PathVariable Long bookingId, @PathVariable String status){
        boolean success = carService.changeBookingStatus(bookingId, status);
        if(success) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/car/search")
    public ResponseEntity<?> searchCar(@RequestBody SearchCarDTO searchCarDTO) {
        return ResponseEntity.ok(carService.searchCar(searchCarDTO));
    }

}
