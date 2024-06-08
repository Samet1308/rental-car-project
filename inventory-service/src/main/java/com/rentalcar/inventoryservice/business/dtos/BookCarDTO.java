package com.rentalcar.inventoryservice.business.dtos;


import com.rentalcar.inventoryservice.enums.BookCarStatus;
import lombok.Data;

import java.util.Date;

@Data
public class BookCarDTO {
    private Long id;

    private Date fromDate;

    private Date toDate;

    private Long price;

    private Long days;

    private BookCarStatus bookCarStatus;

    private Long userId;

    private Long carId;

    private String userName;

    private String email;
}
