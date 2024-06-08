package com.rentalcar.inventoryservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rentalcar.inventoryservice.business.dtos.BookCarDTO;
import com.rentalcar.inventoryservice.business.dtos.UserDTO;
import com.rentalcar.inventoryservice.enums.BookCarStatus;
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
    @JoinColumn(name = "car_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Car car;

    @Transient
    private UserDTO user;

    @Column(name = "user_id")
    private Long userId;

    @PrePersist
    public void prePersist() {
        if (user != null) {
            this.userId = user.getId();
        }
    }

    @Override
    public String toString() {
        return "BookCar{" +
                "id=" + id +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", price=" + price +
                ", days=" + days +
                ", bookCarStatus=" + bookCarStatus +
                ", car=" + (car != null ? car.getId() : "null") +
                ", userId=" + userId +
                '}';
    }


    public BookCarDTO getBookACarDto(){
        BookCarDTO bookACarDto = new BookCarDTO();
        bookACarDto.setId(id);
        bookACarDto.setDays(days);
        bookACarDto.setBookCarStatus(bookCarStatus);
        bookACarDto.setPrice (price);
        bookACarDto.setToDate(toDate);
        bookACarDto.setFromDate (fromDate);
        bookACarDto.setEmail(user.getEmail());
        bookACarDto.setUserName(user.getName());
        bookACarDto.setUserId(user.getId());
        bookACarDto.setCarId(car.getId());
        return bookACarDto;
    }

}
