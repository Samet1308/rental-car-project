package com.rentalcar.inventoryservice.business.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ModelDTO {

    private Long id;

    private String name;

    private MultipartFile image;

    private byte[] returnedImage;

    private Long brandId;

    private String brandName;
}
