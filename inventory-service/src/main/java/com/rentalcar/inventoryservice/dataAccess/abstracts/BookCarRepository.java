package com.rentalcar.inventoryservice.dataAccess.abstracts;


import com.rentalcar.inventoryservice.business.dtos.BookCarDTO;
import com.rentalcar.inventoryservice.entities.BookCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookCarRepository extends JpaRepository<BookCar,Long> {
    List<BookCar> findAllByUserId(Long userId);
}
