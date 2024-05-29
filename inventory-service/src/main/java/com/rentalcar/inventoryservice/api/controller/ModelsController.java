package com.rentalcar.inventoryservice.api.controller;

import com.rentalcar.inventoryservice.business.abstracts.ModelService;
import com.rentalcar.inventoryservice.business.dtos.GetAllModelResponse;
import com.rentalcar.inventoryservice.business.dtos.ModelDTO;
import com.rentalcar.inventoryservice.business.requests.modelRequest.CreateModelRequest;
import com.rentalcar.inventoryservice.business.requests.modelRequest.UpdateModelRequest;
import com.rentalcar.inventoryservice.core.utilities.mappers.ModelMapperService;
import com.rentalcar.inventoryservice.dataAccess.abstracts.ModelRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/inventory")
@AllArgsConstructor
public class ModelsController {

    private ModelService modelService;

    private ModelRepository modelRepository;


    @PostMapping("/{brandId}/model")
    public ResponseEntity<?> postModel(@PathVariable Long brandId , @ModelAttribute ModelDTO modelDTO) throws IOException {
        ModelDTO createdmodelDTO = modelService.postModel(brandId, modelDTO);
        if(createdmodelDTO==null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
        return ResponseEntity.status(HttpStatus.CREATED).body(createdmodelDTO);
    }

    @GetMapping("/{brandId}/models")
    public ResponseEntity<List<ModelDTO>> getAllModelsByBrand(@PathVariable Long brandId){
        List<ModelDTO> modelDtoList = modelService.getAllModelsByBrand(brandId);
        if(modelDtoList==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(modelDtoList);
    }

    @GetMapping("/{brandId}/models/{title}")
    public ResponseEntity<List<ModelDTO>> getAllModelsByBrandAndByTitle(@PathVariable Long brandId, @PathVariable String title){
        List<ModelDTO> productDtoList = modelService.getAllModelsByBrandAndByTitle(brandId,title);
        if(productDtoList==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDtoList);
    }

}
