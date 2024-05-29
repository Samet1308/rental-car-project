package com.rentalcar.customerservice.business.concretes;

import com.rentalcar.customerservice.business.abstracts.UserService;
import com.rentalcar.customerservice.business.dtos.BookCarDTO;
import com.rentalcar.customerservice.business.dtos.CarDto;
import com.rentalcar.customerservice.dataAccess.abstracts.BookCarRepository;
import com.rentalcar.customerservice.dataAccess.abstracts.UserRepository;
import com.rentalcar.customerservice.entities.BookCar;
import com.rentalcar.customerservice.entities.User;
import com.rentalcar.customerservice.enums.BookCarStatus;
import com.rentalcar.customerservice.enums.UserRole;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final CarServiceClient carServiceClient;

    private final BookCarRepository bookCarRepository;


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

    @Override
    public boolean bookCar(BookCarDTO bookCarDTO) {
        Optional<User> optionalUser = userRepository.findById(bookCarDTO.getUserId());
        CarDto carDto = carServiceClient.getCarById(bookCarDTO.getCarId());
        if(optionalUser.isPresent() && carDto != null){

            BookCar bookCar = new BookCar();
            bookCar.setUser(optionalUser.get());
            bookCar.setCar(carDto);
            bookCar.setBookCarStatus(BookCarStatus.PENDING);
            long diffInMilliSeconds = bookCarDTO.getToDate().getTime() - bookCarDTO.getFromDate().getTime();
            long days = TimeUnit.MICROSECONDS.toDays(diffInMilliSeconds);

            bookCar.setDays(days);
            bookCar.setPrice(carDto.getDailyPrice() * days);

            bookCarRepository.save(bookCar);
            return true;

        }
        return false;
    }

}
