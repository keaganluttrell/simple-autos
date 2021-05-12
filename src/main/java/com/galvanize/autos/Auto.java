package com.galvanize.autos;

import jdk.jfr.Enabled;

import javax.persistence.Entity;

@Entity
public class Auto {

    private int year;
    private String make;
    private String model;
    private String vin;

    private String color;
    private String owner;

    public Auto() {}

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

    public String getOwner() {
        return owner;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

}
