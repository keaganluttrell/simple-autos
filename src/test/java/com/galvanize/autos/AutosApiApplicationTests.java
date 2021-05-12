package com.galvanize.autos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

    @Test
    void getAutos_Make_returnsAutoList() {
        String testMake = "ford";
        ResponseEntity<AutosList> response = restTemplate.getForEntity("/api/autos?make=" + testMake, AutosList.class);
        ArrayList<Auto> filtered = filter("make", testMake);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(filtered.size(), response.getBody().getAutos().size());
    }

    @Test
    void getAutos_Color_returnsAutoList() {
        String testColor = "blue";
        ResponseEntity<AutosList> response = restTemplate.getForEntity("/api/autos?color=" + testColor, AutosList.class);
        ArrayList<Auto> filtered = filter("color", testColor);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(filtered.size(), response.getBody().getAutos().size());
    }

    @Test
    void getAutos_MakeColor_returnsAutoList() {
        String testMake = "ford";
        String testColor = "red";
        String uri = String.format("/api/autos?make=%s&color=%s", testMake, testColor);
        ResponseEntity<AutosList> response = restTemplate.getForEntity(uri, AutosList.class);
        ArrayList<Auto> filtered = filterBoth(testMake, testColor);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(filtered.size(), response.getBody().getAutos().size());
    }

    @Test
    void getAutos_Make_returnsNoContent() {
        String uri = String.format("/api/autos?make=%s", "invalid");
        ResponseEntity<AutosList> response = restTemplate.getForEntity(uri, AutosList.class);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void getAutos_Color_returnsNoContent() {
        String uri = String.format("/api/autos?color=%s", "magenta");
        ResponseEntity<AutosList> response = restTemplate.getForEntity(uri, AutosList.class);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void getAutos_MakeColor_returnsNoContent() {
        String uri = String.format("/api/autos?make=%s&color=%s", "ford", "brown");
        ResponseEntity<AutosList> response = restTemplate.getForEntity(uri, AutosList.class);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void addAuto_Auto_returnsNewAuto() throws JsonProcessingException {
        String uri = "/api/autos";
        Auto newAuto = new Auto(1990, "ford", "t", "red", "xdb199x");
        String body = toJSON(newAuto);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Auto> request = new HttpEntity(body, headers);
        ResponseEntity<Auto> response = restTemplate.postForEntity(uri, request, Auto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void addAuto_Auto_BadRequest() {
        String uri = "/api/autos";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Auto> request = new HttpEntity("", headers);
        ResponseEntity<Auto> response = restTemplate.postForEntity(uri, request, Auto.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

""










    public ArrayList<Auto> filter(String key, String val) {
        ArrayList<Auto> foundAutos = new ArrayList<>();
        for (Auto a : autos) {
            if (key.equals("make")) {
                if (a.getMake().equals(val)) {
                    foundAutos.add(a);
                }
            } else if (key.equals("color")) {
                if (a.getColor().equals(val)) {
                    foundAutos.add(a);
                }
            }
        }
        return foundAutos;
    }

    public ArrayList<Auto> filterBoth(String make, String color) {
        ArrayList<Auto> foundAutos = new ArrayList<>();
        for (Auto a : autos) {
            if (a.getMake().equals(make) && a.getColor().equals(color)) {
                foundAutos.add(a);
            }
        }
        return foundAutos;
    }

    public String toJSON(Object auto) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(auto);
    }


}
