package com.rentalcar.inventoryservice.entities;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "cars")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;

    @Column(name = "plate", unique = true)
    private  String plate;

    @Column(name = "daily_price")
    private double dailyPrice;

    @Column(name = "model_year")
    private int modelYear;

    @Column(name = "state_id")
    private Boolean stateId;

    @Column(name = "state_name")
    private String stateName;

    @Column (name = "image_url")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;



}
