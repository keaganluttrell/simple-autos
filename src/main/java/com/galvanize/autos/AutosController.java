package com.galvanize.autos;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/autos")
public class AutosController {

    AutoService autoService;

    public AutosController(AutoService autoService) {
        this.autoService = autoService;
    }

    @GetMapping
    public ResponseEntity<AutosList> getAllAutos(@RequestParam(required = false) String make,
                                                 @RequestParam(required = false) String color) {
        AutosList foundAutos;

        if (color == null && make == null) {
             foundAutos = autoService.getAllAutos();
        } else {
            foundAutos = autoService.getAllAutos(make, color);
        }

        if (foundAutos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(foundAutos);
    }


}
