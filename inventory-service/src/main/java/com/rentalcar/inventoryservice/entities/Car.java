package com.rentalcar.inventoryservice.entities;

import com.rentalcar.inventoryservice.business.dtos.CarDTO;
import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "cars")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    private String name;

    private String color;

    private String type;

    private String transmission;

    @Column(name = "daily_price")
    private Long dailyPrice;

    @Column(name = "model_year")
    private Long modelYear;

    @Column (columnDefinition = "longblob", name = "image")
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    

    public CarDTO getCarDto(){
        CarDTO carDTO = new CarDTO();
        carDTO.setId(id);
        carDTO.setName(name);
        carDTO.setDailyPrice(dailyPrice);
        carDTO.setColor(color);
        carDTO.setType(type);
        carDTO.setReturnedImage(image);
        carDTO.setTransmission(transmission);
        carDTO.setModelYear(modelYear);
        carDTO.setModelName(model.getName());
        carDTO.setModelId(model.getId());

        return carDTO;
    }


}
