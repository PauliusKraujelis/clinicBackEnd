package com.clinicBackEnd.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.clinicBackEnd.controller.PatientController;
import com.clinicBackEnd.entities.Patient;
import com.clinicBackEnd.service.PatientService;

public class PatientControllerTest {

    @Mock
    private PatientService patientService;

    @InjectMocks
    private PatientController patientController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePatient() {
        Patient patient = new Patient();
        doReturn(patient).when(patientService).createPatient(any(Patient.class));

        ResponseEntity<Patient> response = patientController.createPatient(patient);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(patient, response.getBody());
    }

    @Test
    public void testGetPatientById() {
        Long id = 1L;
        Patient patient = new Patient();
        doReturn(patient).when(patientService).getPatientById(anyLong());

        ResponseEntity<Patient> response = patientController.getPatientById(id);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(patient, response.getBody());
    }

    @Test
    public void testGetPatientByIdNotFound() {
        Long id = 1L;
        doReturn(null).when(patientService).getPatientById(anyLong());

        ResponseEntity<Patient> response = patientController.getPatientById(id);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertNull(response.getBody());
    }

    @Test
    public void testGetAllPatients() {
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient());
        patients.add(new Patient());
        doReturn(patients).when(patientService).getAllPatients();

        ResponseEntity<List<Patient>> response = patientController.getAllPatients();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(2, response.getBody().size());
    }

    @Test
    public void testUpdatePatient() {
        Long id = 1L;
        Patient updatedPatient = new Patient();
        doReturn(updatedPatient).when(patientService).updatePatient(anyLong(), any(Patient.class));

        ResponseEntity<Patient> response = patientController.updatePatient(id, updatedPatient);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(updatedPatient, response.getBody());
    }

    @Test
    public void testUpdatePatientNotFound() {
        Long id = 1L;
        doReturn(null).when(patientService).updatePatient(anyLong(), any(Patient.class));

        ResponseEntity<Patient> response = patientController.updatePatient(id, new Patient());

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertNull(response.getBody());
    }

    @Test
    public void testDeletePatient() {
        Long id = 1L;
        doReturn(true).when(patientService).deletePatient(anyLong());

        ResponseEntity<Void> response = patientController.deletePatient(id);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Assertions.assertNull(response.getBody());
    }

    @Test
    public void testDeletePatientNotFound() {
        Long id = 1L;
        doReturn(false).when(patientService).deletePatient(anyLong());

        ResponseEntity<Void> response = patientController.deletePatient(id);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertNull(response.getBody());
    }
}
