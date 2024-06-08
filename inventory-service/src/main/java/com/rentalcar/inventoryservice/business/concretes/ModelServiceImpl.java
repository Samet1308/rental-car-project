package com.rentalcar.inventoryservice.business.concretes;

import com.rentalcar.inventoryservice.business.abstracts.ModelService;
import com.rentalcar.inventoryservice.business.dtos.GetAllModelResponse;
import com.rentalcar.inventoryservice.business.dtos.ModelDTO;
import com.rentalcar.inventoryservice.business.requests.modelRequest.CreateModelRequest;
import com.rentalcar.inventoryservice.business.requests.modelRequest.UpdateModelRequest;
import com.rentalcar.inventoryservice.core.utilities.mappers.ModelMapperService;
import com.rentalcar.inventoryservice.dataAccess.abstracts.BrandRepository;
import com.rentalcar.inventoryservice.dataAccess.abstracts.ModelRepository;
import com.rentalcar.inventoryservice.entities.Brand;
import com.rentalcar.inventoryservice.entities.Model;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ModelServiceImpl implements ModelService {

    private ModelRepository modelRepository;

    private ModelMapperService modelMapperService;

    private BrandRepository brandRepository;


    @Override
    public List<ModelDTO> getAllModels() {
        return modelRepository.findAll().stream().map(Model::getModelDto).collect(Collectors.toList());
    }

    @Override
    public ModelDTO postModel(Long brandId, ModelDTO modelDTO) throws IOException {
        Optional<Brand> optionalBrand = brandRepository.findById(brandId);
        if(optionalBrand.isPresent()){
            Model model = new Model();
            BeanUtils.copyProperties(modelDTO,model);
            model.setImg(modelDTO.getImage().getBytes());
            model.setBrand(optionalBrand.get());
            Model createdModel = modelRepository.save(model);
            ModelDTO createdModelDto = new ModelDTO();
            createdModelDto.setId(createdModel.getId());
            return createdModelDto;
        }

        return null;
    }
    @Override
    public List<ModelDTO> getAllModelsByBrand(Long brandId) {
        return modelRepository.findAllByBrandId(brandId).stream().map(Model::getModelDto).collect(Collectors.toList());
    }
    @Override
    public List<ModelDTO> getAllModelsByBrandAndByTitle(Long brandId, String title) {
        return modelRepository.findAllByBrandIdAndNameContaining(brandId, title).stream().map(Model::getModelDto).collect(Collectors.toList());
    }
}
