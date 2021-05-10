package com.galvanize.autos;

public class Auto {

    private final int year;
    private final String make;
    private final String model;
    private final String vin;

    private String color;
    private String owner;

    public Auto(int year,
                String make,
                String model,
                String color,
                String owner,
                String vin) {
        this.year = year;
        this.make = make;
        this.model = model;
        this.color = color;
        this.owner = owner;
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
