package com.galvanize.autos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutoRepository extends JpaRepository<Auto, Long> {
    List<Auto> findByMakeContainsAndColorContains(String make, String color);
    Auto save(Auto auto);
    Auto findByVin(String vin);
}
