package com.rentalcar.customerservice.business.dtos;

import lombok.Data;

@Data
public class AuthenticationResponse {

    private String jwt;

    private String userRole;

    private Long userId;
}
