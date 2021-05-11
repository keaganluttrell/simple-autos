package com.galvanize.autos;

public class Auto {

    private final int year;
    private final String make;
    private final String model;
    private final String vin;

    private final String color;
    private String owner;

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

    public void setOwner(String owner) {
        this.owner = owner;
    }

}
