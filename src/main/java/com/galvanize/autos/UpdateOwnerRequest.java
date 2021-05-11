package com.galvanize.autos;

public class UpdateOwnerRequest {

    private final String owner;
    private final String color;

    public UpdateOwnerRequest(String owner, String color) {
        this.owner = owner;
        this.color = color;
    }

    public String getOwner() {
        return owner;
    }

    public String getColor() {
        return color;
    }
}
