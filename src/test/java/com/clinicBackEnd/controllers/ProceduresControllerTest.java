package com.clinicBackEnd.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.clinicBackEnd.controller.ProceduresController;
import com.clinicBackEnd.entities.Procedures;
import com.clinicBackEnd.service.ProceduresService;

public class ProceduresControllerTest {

    @Mock
    private ProceduresService proceduresService;

    @InjectMocks
    private ProceduresController proceduresController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateProcedures() {
        Procedures procedures = new Procedures();
        procedures.setId(1L);
        procedures.setName("Procedure Name");
        procedures.setDescription("Procedure Description");
        procedures.setPrice(100.0);

        when(proceduresService.createProcedures(any(Procedures.class))).thenReturn(procedures);

        ResponseEntity<Procedures> responseEntity = proceduresController.createProcedures(procedures);

        assertSame(procedures, responseEntity.getBody());
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void testGetProceduresById() {
        Long id = 1L;
        Procedures procedures = new Procedures();
        procedures.setId(id);
        procedures.setName("Procedure Name");
        procedures.setDescription("Procedure Description");
        procedures.setPrice(100.0);

        when(proceduresService.getProceduresById(id)).thenReturn(procedures);

        ResponseEntity<Procedures> responseEntity = proceduresController.getProceduresById(id);

        assertSame(procedures, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testGetAllProcedures() {
        List<Procedures> proceduresList = new ArrayList<>();
        Procedures procedures1 = new Procedures();
        procedures1.setId(1L);
        procedures1.setName("Procedure 1");
        procedures1.setDescription("Description 1");
        procedures1.setPrice(100.0);

        Procedures procedures2 = new Procedures();
        procedures2.setId(2L);
        procedures2.setName("Procedure 2");
        procedures2.setDescription("Description 2");
        procedures2.setPrice(200.0);

        proceduresList.add(procedures1);
        proceduresList.add(procedures2);

        when(proceduresService.getAllProcedures()).thenReturn(proceduresList);

        ResponseEntity<List<Procedures>> responseEntity = proceduresController.getAllProcedures();

        assertSame(proceduresList, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdateProcedures() {
        Long id = 1L;
        Procedures procedures = new Procedures();
        procedures.setId(id);
        procedures.setName("Procedure Name");
        procedures.setDescription("Procedure Description");
        procedures.setPrice(100.0);

        Procedures updatedProcedures = new Procedures();
        updatedProcedures.setName("Updated Procedure Name");
        updatedProcedures.setDescription("Updated Procedure Description");
        updatedProcedures.setPrice(150.0);

        when(proceduresService.updateProcedures(any(Long.class), any(Procedures.class))).thenReturn(updatedProcedures);

        ResponseEntity<Procedures> responseEntity = proceduresController.updateProcedures(id, updatedProcedures);

        assertSame(updatedProcedures, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdateProceduresNotFound() {
        Long id = 1L;
        Procedures updatedProcedures = new Procedures();
        updatedProcedures.setName("Updated Procedure Name");
        updatedProcedures.setDescription("Updated Procedure Description");
        updatedProcedures.setPrice(150.0);

        when(proceduresService.updateProcedures(any(Long.class), any(Procedures.class))).thenReturn(null);

        ResponseEntity<Procedures> responseEntity = proceduresController.updateProcedures(id, updatedProcedures);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteProcedures() {
        Long id = 1L;

        when(proceduresService.deleteProcedures(id)).thenReturn(true);

        ResponseEntity<Void> responseEntity = proceduresController.deleteProcedures(id);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteProceduresNotFound() {
        Long id = 1L;

        when(proceduresService.deleteProcedures(id)).thenReturn(false);

        ResponseEntity<Void> responseEntity = proceduresController.deleteProcedures(id);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
