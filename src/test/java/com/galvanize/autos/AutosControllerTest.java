package com.galvanize.autos;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
public class AutosControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AutoService autoService;

    /*
        GET /api/autos
        QUERY PARAMS: color, name
          /api/autos?color=blue
          /api/autos?make=ford
          /api/autos?make=ford&color=red
            200: at least one auto exists returns list of all autos matching queries
            204: no autos found
     */



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
