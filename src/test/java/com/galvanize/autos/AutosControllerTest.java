package com.galvanize.autos;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AutosController.class)
public class AutosControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AutoService autoService;

    List<Auto> autos;

    @BeforeEach
    void setUp() {
        autos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Auto auto = new Auto(1990, "ford", "t", "red", "joe", "123456789"+i);
            autos.add(auto);
        }
    }

    // GET /api/autos
    // QUERY PARAMS: color, name
    // /api/autos?color=blue
    // /api/autos?make=ford
    // /api/autos?make=ford&color=red
    // 204: no autos found

    // GET /api/autos 200: at least one auto exists returns list of all autos matching queries
    @Test
    void getAllAutos_noParams_returnsList() throws Exception {
//        ArrayList<Auto> autosNew = new ArrayList<>();
//        Auto auto = new Auto(1990, "ford", "t", "red", "joe", "123456789");
//        autosNew.add(auto);

        when(autoService.getAllAutos()).thenReturn(new AutosList(autos));
        System.out.print(autos);

        mockMvc.perform(get("/api/autos"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)));
    }

    // GET /api/autos ->  204: returns no autos found
    @Test
    void getAllAutos_noParams_returnNoContent() throws Exception {
        when(autoService.getAllAutos()).thenReturn(new AutosList());

        mockMvc.perform(get("/api/autos"))
                .andExpect(status().isNoContent());
    }

/*
        POST /api/autos
        NO PARAMS
        BODY: takes an automobile schema object
           200 adds auto successfully returns auto
           400 Bad request return error message
     */

    /*
        GET /api/autos/{vin}
        PATH: {vin} required
          200 returns an auto that matches vin property
          204 auto not found
     */

    /*
        PATCH /api/autos/{vin}
        PATH: {vin} required
        BODY: auto properties to be updated
          200 returns an updated auto that matches vin property
          204 auto not found
          400 bad request returns message
     */

        /*
        DELETE /api/autos/{vin}
        PATH: {vin} required
          202 auto delete accepted
          204 auto not found
     */
}
