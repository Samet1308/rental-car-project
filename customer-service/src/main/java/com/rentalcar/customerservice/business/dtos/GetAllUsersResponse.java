package com.rentalcar.customerservice.business.dtos;

import com.rentalcar.customerservice.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllUsersResponse {

    private Long id;

    private String name;

    private String email;

    private UserRole userRole;
}
