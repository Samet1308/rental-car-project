package com.rentalcar.customerservice.api.controller;

import com.rentalcar.customerservice.business.abstracts.UserService;
import com.rentalcar.customerservice.business.dtos.GetAllUsersResponse;
import com.rentalcar.customerservice.business.requests.CreateUserRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/auth")
public class UserController {

    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signupCustomer(@RequestBody CreateUserRequest createUserRequest){
        if(userService.hasCustomerWithEmail(createUserRequest.getEmail()))
            return new ResponseEntity<>("Customer is already exist with this email",HttpStatus.NOT_ACCEPTABLE);
        GetAllUsersResponse createdCustomerDTO = userService.add(createUserRequest);
        if(createdCustomerDTO == null) return new ResponseEntity<>
                ("Customer is not created. Try again later.", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(createdCustomerDTO,HttpStatus.CREATED);

    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        this.userService.delete(id);
    }

}
