package com.galvanize.autos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AutoServiceTest {

    AutoService autoService;

    @Mock
    AutoRepository autoRepository;

    @BeforeEach
    void setUp() {
        autoService = new AutoService(autoRepository);
    }

    @Test
    void getAllAutos() {
        Auto auto = new Auto(1999, "ford", "t", "red", "0001abc");
        when(autoRepository.findAll()).thenReturn(Arrays.asList(auto));
        AutosList autosList = autoService.getAllAutos();
        assertNotNull(autosList);
        assertFalse(autosList.isEmpty());
    }

    @Test
    void getAutos_withSearchParams_returnsList() {
        Auto auto = new Auto(1999, "ford", "t", "red", "0001abc");

        when(autoRepository.findByMakeContainsAndColorContains(anyString(), anyString())).thenReturn(Arrays.asList(auto));
        AutosList autosList = autoService.getAllAutos(auto.getMake(), auto.getColor());
        assertNotNull(autosList);
        assertFalse(autosList.isEmpty());
    }

    @Test
    void getAutos_withSearchParams_returnsEmptyList() {
        when(autoRepository.findByMakeContainsAndColorContains(anyString(), anyString())).thenReturn(new ArrayList<>());
        AutosList autosList = autoService.getAllAutos("honda", "yellow");
        assertNotNull(autosList);
        assertTrue(autosList.isEmpty());
    }

    @Test
    void postAuto_valid_returnsAuto() {
        Auto auto = new Auto(1999, "ford", "t", "red", "0001abc");
        when(autoRepository.save(any(Auto.class))).thenReturn(auto);

        Auto returnedAuto = autoService.addAuto(auto);

        assertNotNull(returnedAuto);
        assertEquals(auto.getMake(), returnedAuto.getMake());
    }
}
