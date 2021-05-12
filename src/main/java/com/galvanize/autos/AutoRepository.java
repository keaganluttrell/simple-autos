package com.galvanize.autos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutoRepository extends JpaRepository<Auto, Long> {

    @Query(nativeQuery = true, value = "select * from autos where make like ? and color like ?")
    List<Auto> findByMakeContainsAndColorContains(String make, String color);

    Auto findByVin(String vin);
}
