package com.rentalcar.inventoryservice.business.dtos;

import com.rentalcar.inventoryservice.entities.Model;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class BrandDTO {

    private Long id;

    private String name;

    private MultipartFile image;

    private byte[] returnedImage;
}

