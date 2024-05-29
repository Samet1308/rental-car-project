package com.rentalcar.customerservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rentalcar.customerservice.business.dtos.CarDto;
import com.rentalcar.customerservice.enums.BookCarStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Table(name="book_car")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BookCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fromDate;

    private Date toDate;

    private Long price;

    private Long days;

    private BookCarStatus bookCarStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @Transient
    private CarDto car;


}
