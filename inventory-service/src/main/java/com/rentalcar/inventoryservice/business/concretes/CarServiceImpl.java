package com.rentalcar.inventoryservice.business.concretes;

import com.rentalcar.inventoryservice.business.abstracts.CarService;
import com.rentalcar.inventoryservice.business.dtos.GetAllCarResponse;
import com.rentalcar.inventoryservice.business.requests.carRequest.CreateCarRequest;
import com.rentalcar.inventoryservice.business.requests.carRequest.UpdateCarRequest;
import com.rentalcar.inventoryservice.core.utilities.mappers.ModelMapperService;
import com.rentalcar.inventoryservice.dataAccess.abstracts.CarRepository;
import com.rentalcar.inventoryservice.entities.Car;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;

    private ModelMapperService modelMapperService;

    @Override
    public List<GetAllCarResponse> getAll(){
        List<Car> cars = this.carRepository.findAll();
        List<GetAllCarResponse> carResponses = cars.stream()
                .map(car -> this.modelMapperService.forResponse()
                        .map(car, GetAllCarResponse.class)).collect(Collectors.toList());
        return carResponses;
    }
    @Override
    public void add(CreateCarRequest createCarRequest){
        Car car = this.modelMapperService.forRequest().map(createCarRequest,Car.class);
        this.carRepository.save(car);
    }
    @Override
    public void update(UpdateCarRequest updateCarRequest){
        Car car = this.modelMapperService.forRequest().map(updateCarRequest,Car.class);
        this.carRepository.save(car);
    }
    @Override
    public void delete(@PathVariable int id){
    this.carRepository.deleteById(id);
    }
}
