package com.galvanize.autos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "autos")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int year;
    private String make;
    private String model;
    private String vin;
    private String color;

    @Column(name = "owner_name")
    private String owner;

    @JsonFormat(pattern = "mm/dd/yyyy")
    private Date purchaseDate;

    public Auto() {
    }

    public Auto(int year,
                String make,
                String model,
                String color,
                String vin) {
        this.year = year;
        this.make = make;
        this.model = model;
        this.color = color;
        this.vin = vin;
    }

    public int getYear() {
        return year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getVin() {
        return vin;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
