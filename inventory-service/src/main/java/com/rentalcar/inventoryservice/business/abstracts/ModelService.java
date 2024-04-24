package com.rentalcar.inventoryservice.business.abstracts;

import com.rentalcar.inventoryservice.business.dtos.GetAllModelResponse;
import com.rentalcar.inventoryservice.business.requests.modelRequest.CreateModelRequest;
import com.rentalcar.inventoryservice.business.requests.modelRequest.UpdateModelRequest;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ModelService {
    List<GetAllModelResponse> getAll();

    void add(CreateModelRequest createModelRequest);

    void update(UpdateModelRequest updateModelRequest);

    void delete(@PathVariable int id);
}
