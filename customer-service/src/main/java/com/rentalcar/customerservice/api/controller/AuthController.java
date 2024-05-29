package com.rentalcar.customerservice.api.controller;

import com.rentalcar.customerservice.business.abstracts.AuthService;
import com.rentalcar.customerservice.business.dtos.AuthenticationResponse;
import com.rentalcar.customerservice.business.dtos.GetAllUsersResponse;
import com.rentalcar.customerservice.business.requests.AuthenticationRequest;
import com.rentalcar.customerservice.business.requests.CreateUserRequest;
import com.rentalcar.customerservice.core.utilities.utils.JWTUtil;
import com.rentalcar.customerservice.dataAccess.abstracts.UserRepository;
import com.rentalcar.customerservice.entities.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService authService;

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final JWTUtil jwtUtil;

    private UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> signupCustomer(@RequestBody CreateUserRequest createUserRequest) {
        if (authService.hasCustomerWithEmail(createUserRequest.getEmail()))
            return new ResponseEntity<>("Customer is already exist with this email", HttpStatus.NOT_ACCEPTABLE);
        GetAllUsersResponse createdCustomerDTO = this.authService.add(createUserRequest);
        if (createdCustomerDTO == null) return new ResponseEntity<>
                ("Customer is not created. Try again later.", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(createdCustomerDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws
            BadCredentialsException,
            DisabledException,
            UsernameNotFoundException {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                    (authenticationRequest.getEmail(),
                     authenticationRequest.getPassword()));
        }catch (BadCredentialsException exception){
            throw new BadCredentialsException("Incorrect email or password");
        }
        final UserDetails userDetails = this.userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        Optional<User> optionalUser = this.userRepository.findFirstByEmail(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if(optionalUser.isPresent()){
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setUserId(optionalUser.get().getId());
            authenticationResponse.setUserRole(String.valueOf((optionalUser.get().getUserRole())));
        }
        return authenticationResponse;
    }



    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        this.authService.delete(id);
    }

}
