package com.rentalcar.inventoryservice.business.concretes;

import com.rentalcar.inventoryservice.business.abstracts.ModelService;
import com.rentalcar.inventoryservice.business.dtos.GetAllModelResponse;
import com.rentalcar.inventoryservice.business.requests.modelRequest.CreateModelRequest;
import com.rentalcar.inventoryservice.business.requests.modelRequest.UpdateModelRequest;
import com.rentalcar.inventoryservice.core.utilities.mappers.ModelMapperService;
import com.rentalcar.inventoryservice.dataAccess.abstracts.ModelRepository;
import com.rentalcar.inventoryservice.entities.Model;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ModelServiceImpl implements ModelService {

    private ModelRepository modelRepository;

    private ModelMapperService modelMapperService;

    @Override
    public List<GetAllModelResponse> getAll(){
        List<Model> models = this.modelRepository.findAll();
        List<GetAllModelResponse> modelResponses = models.stream()
                .map(model  -> this.modelMapperService.forResponse()
                        .map(model, GetAllModelResponse.class)).collect(Collectors.toList());
        return modelResponses;
    }

    @Override
    public void add(CreateModelRequest createModelRequest){
        Model model = this.modelMapperService.forRequest().map(createModelRequest,Model.class);
        this.modelRepository.save(model);
    }
    @Override
    public void update(UpdateModelRequest updateModelRequest){
        Model model = this.modelMapperService.forRequest().map(updateModelRequest, Model.class);
        this.modelRepository.save(model);
    }

    @Override
    public void delete(@PathVariable int id){
        this.modelRepository.deleteById(id);
    }
}
