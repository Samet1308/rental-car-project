package com.rentalcar.customerservice.dataAccess.abstracts;

import com.rentalcar.customerservice.entities.User;
import com.rentalcar.customerservice.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User,Long> {

    Optional<User> findFirstByEmail(String email);

    User findByUserRole(UserRole userRole);
}
