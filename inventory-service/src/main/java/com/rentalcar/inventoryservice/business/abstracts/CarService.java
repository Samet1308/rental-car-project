package com.rentalcar.inventoryservice.business.abstracts;

import com.rentalcar.inventoryservice.business.dtos.GetAllCarResponse;
import com.rentalcar.inventoryservice.business.requests.carRequest.CreateCarRequest;
import com.rentalcar.inventoryservice.business.requests.carRequest.UpdateCarRequest;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CarService {

    List<GetAllCarResponse> getAll();

    void add(CreateCarRequest createCarRequest);

    void update(UpdateCarRequest updateCarRequest);

    void delete(@PathVariable int id);
}
