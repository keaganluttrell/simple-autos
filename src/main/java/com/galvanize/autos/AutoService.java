package com.galvanize.autos;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AutoService {

    private ArrayList<Auto> automobiles = new ArrayList<>();

    public AutoService() {}

    public List<Auto> getAllAutos() {
        return automobiles;
    }
}
