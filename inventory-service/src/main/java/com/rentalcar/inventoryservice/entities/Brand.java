package com.rentalcar.inventoryservice.entities;

import com.rentalcar.inventoryservice.business.dtos.BrandDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Table(name="brands")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column (name = "name")
    private String name;

    @Lob
    @Column(name = "image",columnDefinition = "longblob")
    private byte[] image;

    @OneToMany(mappedBy = "brand")
    private List<Model> models;

    public BrandDTO getBrandDto(){
        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setId(id);
        brandDTO.setName(name);
        brandDTO.setReturnedImage(image);
        return brandDTO;

    }

}
