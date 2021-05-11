package com.galvanize.autos;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/autos")
public class AutosController {

    AutoService autoService;

    public AutosController(AutoService autoService) {
        this.autoService = autoService;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void InvalidAutoExceptionHandler(InvalidAutoException exception) {
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

    @PostMapping
    public Auto addAuto(@RequestBody Auto auto) {
        return autoService.addAuto(auto);
    }

    @GetMapping("/{vin}")
    public ResponseEntity<Auto> getAutoByVin(@PathVariable String vin) {
        Auto foundAuto = autoService.getAutoByVin(vin);
        if (foundAuto == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(foundAuto);
        }
    }

    @PatchMapping("/{vin}")
    public ResponseEntity<Auto> updateAuto(@PathVariable String vin,
                                           @RequestBody UpdateOwnerRequest request) {

        Auto foundAuto = autoService.updateAuto(vin, request.getOwner(), request.getColor());
        if (foundAuto == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(foundAuto);
        }

    }

    @DeleteMapping("/{vin}")
    public ResponseEntity deleteAuto(@PathVariable String vin) {
        autoService.deleteAuto(vin);

        return ResponseEntity.accepted().build();
    }

}
