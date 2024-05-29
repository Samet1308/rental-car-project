package com.rentalcar.customerservice.dataAccess.abstracts;

import com.rentalcar.customerservice.entities.BookCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCarRepository extends JpaRepository<BookCar,Long> {
}
