package com.clinicBackEnd.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ProceduresTest {

    @Test
    public void testProceduresGettersAndSetters() {
        Long id = 1L;
        String name = "Procedure Name";
        String description = "Procedure Description";
        double price = 100.0;
        
        Procedures procedures = new Procedures();
        procedures.setId(id);
        procedures.setName(name);
        procedures.setDescription(description);
        procedures.setPrice(price);
        
        assertEquals(id, procedures.getId());
        assertEquals(name, procedures.getName());
        assertEquals(description, procedures.getDescription());
        assertEquals(price, procedures.getPrice(), 0.001);
    }
}
