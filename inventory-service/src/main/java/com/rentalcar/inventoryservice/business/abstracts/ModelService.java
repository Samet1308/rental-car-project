package com.rentalcar.inventoryservice.business.abstracts;

import com.rentalcar.inventoryservice.business.dtos.GetAllModelResponse;
import com.rentalcar.inventoryservice.business.dtos.ModelDTO;
import com.rentalcar.inventoryservice.business.requests.modelRequest.CreateModelRequest;
import com.rentalcar.inventoryservice.business.requests.modelRequest.UpdateModelRequest;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.List;

public interface ModelService {


    ModelDTO postModel(Long brandId, ModelDTO modelDTO) throws IOException;

    List<ModelDTO> getAllModelsByBrand(Long brandId);

    List<ModelDTO> getAllModelsByBrandAndByTitle(Long brandId, String title);

}
