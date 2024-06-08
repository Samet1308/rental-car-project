package com.rentalcar.customerservice.api.controller;

import com.rentalcar.customerservice.business.abstracts.UserService;
import com.rentalcar.customerservice.dataAccess.abstracts.UserRepository;
import com.rentalcar.customerservice.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

//    @PostMapping("/car/book/{carId}")
//    public ResponseEntity<?> bookCar(@PathVariable Long carId, @RequestBody BookCarDTO bookCarDTO) {
//        boolean success = userService.bookCar(carId, bookCarDTO);
//        if (success) return ResponseEntity.status(HttpStatus.CREATED).build();
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//    }

}