package com.rentalcar.inventoryservice.business.concretes;

import com.rentalcar.inventoryservice.business.abstracts.CarService;
import com.rentalcar.inventoryservice.business.dtos.*;
import com.rentalcar.inventoryservice.core.utilities.mappers.ModelMapperService;
import com.rentalcar.inventoryservice.dataAccess.abstracts.BookCarRepository;
import com.rentalcar.inventoryservice.dataAccess.abstracts.CarRepository;
import com.rentalcar.inventoryservice.dataAccess.abstracts.ModelRepository;
import com.rentalcar.inventoryservice.entities.BookCar;
import com.rentalcar.inventoryservice.entities.Car;
import com.rentalcar.inventoryservice.entities.Model;
import com.rentalcar.inventoryservice.enums.BookCarStatus;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;

    private ModelRepository modelRepository;

    private BookCarRepository bookCarRepository;

    @Autowired
    private UserClient userClient;

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
    @Override
    public boolean bookCar(BookCarDTO bookCarDTO) {
        UserDTO user = userClient.getUserById(bookCarDTO.getUserId());
        Optional<Car> optionalCar = carRepository.findById(bookCarDTO.getCarId());

        if (user != null && optionalCar.isPresent()) {
            Car existingCar = optionalCar.get();
            BookCar bookCar = new BookCar();
            bookCar.setUser(user);
            bookCar.setCar(existingCar);
            bookCar.setFromDate(bookCarDTO.getFromDate());
            bookCar.setToDate(bookCarDTO.getToDate());
            bookCar.setBookCarStatus(BookCarStatus.PENDING);

            long diffInMilliSeconds = bookCarDTO.getToDate().getTime() - bookCarDTO.getFromDate().getTime();
            long days = TimeUnit.MILLISECONDS.toDays(diffInMilliSeconds);

            bookCar.setDays(days);
            bookCar.setPrice(existingCar.getDailyPrice() * days);

            bookCarRepository.save(bookCar);
            return true;
        }
        return false;
    }

    @Override
    public List<BookCarDTO> getBookingsByUserId(Long userId) {
        List<BookCar> bookCars = bookCarRepository.findAllByUserId(userId);
        UserDTO user = userClient.getUserById(userId);
        return bookCars.stream().map(bookCar -> {
            bookCar.setUser(user); // Set user before converting to DTO
            return bookCar.getBookACarDto();
        }).collect(Collectors.toList());
    }

    @Override
    public List<BookCarDTO> getBookings() {
        List<BookCar> bookCars = bookCarRepository.findAll();
        return bookCars.stream().map(bookCar -> {
            UserDTO user = userClient.getUserById(bookCar.getUserId());
            bookCar.setUser(user); // User'ı DTO'ya dönüştürmeden önce ayarla
            return bookCar.getBookACarDto();
        }).collect(Collectors.toList());
    }

    @Override
    public boolean changeBookingStatus(Long bookingId, String status) {
        Optional<BookCar> optionalBookCar = bookCarRepository.findById(bookingId);
        if(optionalBookCar.isPresent()){
            BookCar existingBookCar = optionalBookCar.get();
            if(Objects.equals(status,"Onayla")){
                existingBookCar.setBookCarStatus(BookCarStatus.APPROVED);
            }else
                existingBookCar.setBookCarStatus(BookCarStatus.REJECTED);
            bookCarRepository.save(existingBookCar);
            return true;
        }
        return false;
    }

    @Override
    public CarDtoListDto searchCar(SearchCarDTO searchCarDTO) {
        Car car = new Car();
        car.setModelYear(searchCarDTO.getModelYear());
        car.setType(searchCarDTO.getType());
        car.setTransmission(searchCarDTO.getTransmission());
        car.setColor(searchCarDTO.getColor());
        ExampleMatcher exampleMatcher =
                ExampleMatcher.matchingAll()
                        .withMatcher("model", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                        .withMatcher("type", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                        .withMatcher("modelYear", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                        .withMatcher("transmission", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                        .withMatcher("color", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        Example<Car> carExample = Example.of(car,exampleMatcher);
        List<Car> carList = carRepository.findAll(carExample);
        CarDtoListDto carDtoListDto = new CarDtoListDto();
        carDtoListDto.setCarDTOList(carList.stream().map(Car::getCarDto).collect(Collectors.toList()));
        return carDtoListDto;
    }


}
