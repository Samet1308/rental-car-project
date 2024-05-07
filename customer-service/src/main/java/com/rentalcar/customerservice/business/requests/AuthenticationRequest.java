package com.rentalcar.customerservice.business.requests;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String email;

    private String password;
}
