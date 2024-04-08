package com.rentalcar.inventoryservice.entities;

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
    private int id;

    @Column (name = "name")
    private String name;

    @OneToMany(mappedBy = "brand")
    private List<Model> models;
}
