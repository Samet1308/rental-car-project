package com.rentalcar.customerservice.business.concretes;

import com.rentalcar.customerservice.business.dtos.CarDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CarServiceClient {
    private final RestTemplate restTemplate;

    @Autowired
    public CarServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CarDto getCarById(Long carId) {
        String url = "http://inventory-service/api/inventory/car/" + carId;
        ResponseEntity<CarDto> response = restTemplate.getForEntity(url, CarDto.class);
        return response.getBody();
    }
}
