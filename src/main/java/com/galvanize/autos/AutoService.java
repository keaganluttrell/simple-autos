package com.galvanize.autos;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AutoService {

//    public AutoService() {}

    public AutosList getAllAutos() {
        return null;
    }

    public AutosList getAllAutos(String make, String color) {
        return null;
    }

    public Auto addAuto(Auto auto) {
        return null;
    }

    public Auto getAutoByVin(String vin) {
        return null;
    }

    public Auto updateAuto(String vin, String color, String owner) {
        return null;
    }

    public void deleteAuto(String vin) {
    }
}