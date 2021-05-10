package com.galvanize.autos;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/autos")
public class AutosController {

    AutoService autoService = new AutoService();

    @GetMapping
    public ResponseEntity<AutosList> getAllAutos() {
        AutosList foundAutos = autoService.getAllAutos();
        if (foundAutos.getAutos().size() == 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(foundAutos);
    }

}
