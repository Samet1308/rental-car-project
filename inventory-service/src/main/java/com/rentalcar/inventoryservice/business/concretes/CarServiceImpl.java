package com.rentalcar.inventoryservice.business.concretes;

import com.rentalcar.inventoryservice.business.abstracts.CarService;
import com.rentalcar.inventoryservice.business.dtos.CarDTO;
import com.rentalcar.inventoryservice.core.utilities.mappers.ModelMapperService;
import com.rentalcar.inventoryservice.dataAccess.abstracts.CarRepository;
import com.rentalcar.inventoryservice.dataAccess.abstracts.ModelRepository;
import com.rentalcar.inventoryservice.entities.Car;
import com.rentalcar.inventoryservice.entities.Model;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;

    private ModelRepository modelRepository;




    @Override
    public List<CarDTO> getAllCarsByModel(Long modelId) {
        return carRepository.findAllByModelId(modelId).stream().map(Car::getCarDto).collect(Collectors.toList());
    }

    @Override
    public CarDTO postCar(Long modelId, CarDTO carDTO) throws IOException {


        Optional<Model> optionalModel = modelRepository.findById(modelId);
        if (optionalModel.isPresent()) {
            Car car = new Car();
            BeanUtils.copyProperties(carDTO,car);
            car.setModel(optionalModel.get());
            car.setImage(carDTO.getImage().getBytes());
            car.setName(car.getName());
            car.setColor(carDTO.getColor());
            car.setType(carDTO.getType());
            car.setTransmission(carDTO.getTransmission());
            car.setDailyPrice(carDTO.getDailyPrice());
            car.setModelYear(carDTO.getModelYear());

            Car createdCar = carRepository.save(car);
            CarDTO createdCarDto = new CarDTO();
            createdCarDto.setId(createdCar.getId());
            return createdCarDto ;

        }
        return null;
    }

    @Override
    public void deleteCar(Long carId) {
        Optional<Car> optionalProduct = carRepository.findById(carId);
        if(optionalProduct.isPresent()){
            carRepository.deleteById(carId);
        }
    }

    @Override
    public CarDTO getCarById(Long carId) {
        Optional<Car> optionalCar = carRepository.findById(carId);
        return optionalCar.map(Car::getCarDto).orElse(null);
    }


    @Override
    public boolean updateCar (Long carId, CarDTO carDto) throws IOException {
        Optional<Car> optionalCar = carRepository.findById(carId);
        if (optionalCar.isPresent()) {
            Car existingCar = optionalCar.get();
            if (carDto.getImage() != null)
                existingCar.setImage(carDto.getImage().getBytes());
            existingCar.setDailyPrice(carDto.getDailyPrice());
            existingCar.setModelYear(carDto.getModelYear());
            existingCar.setType(carDto.getType());
            existingCar.setTransmission(carDto.getTransmission());
            existingCar.setColor(carDto.getColor());
            existingCar.setName(carDto.getName());
            carRepository.save(existingCar);
            return true;
        } else {
            return false;
        }
    }



}
