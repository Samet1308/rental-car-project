package com.rentalcar.inventoryservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rentalcar.inventoryservice.business.dtos.ModelDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Table(name = "models")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column (name = "name")
    private String name;

    @Lob
    @Column(name = "img",columnDefinition = "longblob")
    private byte[] img;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "brand_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Brand brand;

    @OneToMany(mappedBy = "model")
    private List<Car> cars;

    public ModelDTO getModelDto(){
        ModelDTO modelDTO = new ModelDTO();
        modelDTO.setId(id);
        modelDTO.setName(name);
        modelDTO.setReturnedImage(img);
        modelDTO.setBrandId(brand.getId());
        modelDTO.setBrandName(brand.getName());
        return modelDTO;

    }
}
