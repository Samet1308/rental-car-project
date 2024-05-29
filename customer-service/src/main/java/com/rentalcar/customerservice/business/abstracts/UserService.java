package com.rentalcar.customerservice.business.abstracts;

import com.rentalcar.customerservice.business.dtos.BookCarDTO;
import com.rentalcar.customerservice.business.dtos.CarDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    boolean bookCar(BookCarDTO bookCarDTO);

}
