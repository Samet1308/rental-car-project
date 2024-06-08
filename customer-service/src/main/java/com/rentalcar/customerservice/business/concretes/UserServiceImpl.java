package com.rentalcar.customerservice.business.concretes;

import com.rentalcar.customerservice.business.abstracts.UserService;
import com.rentalcar.customerservice.dataAccess.abstracts.UserRepository;
import com.rentalcar.customerservice.entities.User;
import com.rentalcar.customerservice.enums.UserRole;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @PostConstruct
    public void createAdminAccount(){
        User adminAccount = this.userRepository.findByUserRole(UserRole.ADMIN);
        if(adminAccount == null){
            User newAdminAccount = new User();
            newAdminAccount.setName("admin");
            newAdminAccount.setEmail("admin@test.com");
            newAdminAccount.setPassword(new BCryptPasswordEncoder().encode("admin"));
            newAdminAccount.setUserRole(UserRole.ADMIN);

            userRepository.save(newAdminAccount);
            System.out.println("Admin oluşturuldu.");
        }
    }



}
