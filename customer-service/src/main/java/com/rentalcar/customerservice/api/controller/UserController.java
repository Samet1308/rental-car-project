package com.rentalcar.customerservice.api.controller;

import com.rentalcar.customerservice.business.abstracts.UserService;
import com.rentalcar.customerservice.business.concretes.CarServiceClient;
import com.rentalcar.customerservice.business.dtos.BookCarDTO;
import com.rentalcar.customerservice.business.dtos.CarDto;
import com.rentalcar.customerservice.dataAccess.abstracts.UserRepository;
import com.rentalcar.customerservice.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/car/book")
    public ResponseEntity<Void> bookCar(@RequestBody BookCarDTO bookCarDTO) {
        boolean success = userService.bookCar(bookCarDTO);
        if (success) return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}