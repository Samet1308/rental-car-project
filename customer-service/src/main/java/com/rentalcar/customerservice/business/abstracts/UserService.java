package com.rentalcar.customerservice.business.abstracts;

import com.rentalcar.customerservice.business.dtos.GetAllUsersResponse;
import com.rentalcar.customerservice.business.requests.CreateUserRequest;

public interface UserService {
    GetAllUsersResponse add(CreateUserRequest createUserRequest);

    boolean hasCustomerWithEmail(String email);
    void delete(Long id);
}
