package com.galvanize.autos;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AutosApiApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    AutoRepository autoRepository;

    List<Auto> autos;

    @BeforeEach
    void setup() {
        autos = new ArrayList<>();
        Auto auto;
        String[] colors = {"red", "blue", "green", "yellow", "black"};
        String[] makes = {"ford", "chevy", "honda", "toyota", "jeep"};
        for (int i = 0; i < 5; i++) {
            auto = new Auto(1990 + i, makes[i], "model", colors[i], "xdb199" + i);
            this.autos.add(auto);
        }
        autoRepository.saveAll(autos);
    }

    @AfterEach
    void teardown() {
        autoRepository.deleteAll();
    }

    @Test
    void contextLoads() {
    }

    @Test
    void getAutos_noParams_returnsAutoList() {
        ResponseEntity<AutosList> response = restTemplate.getForEntity("/api/autos", AutosList.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(autos.size(), response.getBody().getAutos().size());
    }

    @Test
    void getAutos_noParams_returnsNoContent() {
        autoRepository.deleteAll();
        ResponseEntity<AutosList> response = restTemplate.getForEntity("/api/autos", AutosList.class);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }


}
