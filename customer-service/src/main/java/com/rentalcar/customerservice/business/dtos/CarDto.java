package com.rentalcar.customerservice.business.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CarDto {


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
