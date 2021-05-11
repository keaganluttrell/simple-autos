package com.galvanize.autos;

public class UpdateOwnerRequest {

    private String owner;
    private String color;

    public UpdateOwnerRequest() {}

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
