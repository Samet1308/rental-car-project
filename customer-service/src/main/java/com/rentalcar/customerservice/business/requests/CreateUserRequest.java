package com.rentalcar.customerservice.business.requests;

import lombok.Data;

@Data
public class CreateUserRequest {

    private String email;

    private String name;

    private String password;
}
