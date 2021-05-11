package com.galvanize.autos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
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
}
