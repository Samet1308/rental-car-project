package com.rentalcar.customerservice.business.concretes;

import com.rentalcar.customerservice.business.abstracts.AuthService;
import com.rentalcar.customerservice.business.dtos.GetAllUsersResponse;
import com.rentalcar.customerservice.business.requests.CreateUserRequest;
import com.rentalcar.customerservice.dataAccess.abstracts.UserRepository;
import com.rentalcar.customerservice.entities.User;
import com.rentalcar.customerservice.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    @Override
    public GetAllUsersResponse add(CreateUserRequest createUserRequest) {
        User user = new User();
        user.setName(createUserRequest.getName());
        user.setEmail(createUserRequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(createUserRequest.getPassword()));
        user.setUserRole(UserRole.CUSTOMER);

        User createdUser = userRepository.save(user);
        GetAllUsersResponse usersResponse = new GetAllUsersResponse();
        usersResponse.setId(createdUser.getId());


        return usersResponse;
    }

    @Override
    public boolean hasCustomerWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }

    @Override
    public void delete(@PathVariable Long id) {
        this.userRepository.deleteById(id);
    }



}
