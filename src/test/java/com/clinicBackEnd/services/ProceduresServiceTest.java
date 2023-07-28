package com.clinicBackEnd.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.clinicBackEnd.entities.Procedures;
import com.clinicBackEnd.repository.ProceduresRepository;
import com.clinicBackEnd.service.ProceduresService;

public class ProceduresServiceTest {

    @Mock
    private ProceduresRepository proceduresRepository;

    @InjectMocks
    private ProceduresService proceduresService;

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

        when(proceduresRepository.save(any(Procedures.class))).thenReturn(procedures);

        Procedures createdProcedures = proceduresService.createProcedures(procedures);

        assertEquals(procedures, createdProcedures);
    }

    @Test
    public void testGetProceduresById() {
        Long id = 1L;
        Procedures procedures = new Procedures();
        procedures.setId(id);
        procedures.setName("Procedure Name");
        procedures.setDescription("Procedure Description");
        procedures.setPrice(100.0);

        when(proceduresRepository.findById(id)).thenReturn(Optional.of(procedures));

        Procedures foundProcedures = proceduresService.getProceduresById(id);

        assertEquals(procedures, foundProcedures);
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

        when(proceduresRepository.findAll()).thenReturn(proceduresList);

        List<Procedures> foundProceduresList = proceduresService.getAllProcedures();

        assertEquals(proceduresList, foundProceduresList);
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

        when(proceduresRepository.findById(id)).thenReturn(Optional.of(procedures));
        when(proceduresRepository.save(any(Procedures.class))).thenReturn(updatedProcedures);

        Procedures result = proceduresService.updateProcedures(id, updatedProcedures);

        assertEquals(updatedProcedures, result);
    }

    @Test
    public void testDeleteProcedures() {
        Long id = 1L;

        when(proceduresRepository.existsById(id)).thenReturn(true);

        boolean result = proceduresService.deleteProcedures(id);

        assertEquals(true, result);
        verify(proceduresRepository, times(1)).deleteById(id);
    }
}
