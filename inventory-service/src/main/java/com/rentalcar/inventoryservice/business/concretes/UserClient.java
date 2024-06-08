package com.rentalcar.inventoryservice.business.concretes;

import com.rentalcar.inventoryservice.business.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserClient {

    @Autowired
    private RestTemplate restTemplate;

    public UserDTO getUserById(Long userId) {
        String url = "http://localhost:8082/api/users/" + userId;
        return restTemplate.getForObject(url, UserDTO.class);
    }
}
