package com.galvanize.autos;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoService {

    AutoRepository autoRepository;

    public AutoService() {
    }

    public AutoService(AutoRepository autoRepository) {
        this.autoRepository = autoRepository;
    }

    public AutosList getAllAutos() {
        return new AutosList(autoRepository.findAll());
    }

    public AutosList getAllAutos(String make, String color) {
        List<Auto> autos = autoRepository.findByMakeContainsAndColorContains(make, color);
        return new AutosList(autos);
    }

    public Auto addAuto(Auto auto) {
        return autoRepository.save(auto);
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
