package com.rentalcar.inventoryservice.business.dtos;

import com.rentalcar.inventoryservice.entities.Model;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class CarDTO {


    private Long id;

    private String name;

    private Long dailyPrice;

    private Long modelYear;

    private String color;

    private String type;

    private String transmission;

    private MultipartFile image;

    private  byte[] returnedImage;

    private String modelName;

    private Long modelId;


}
