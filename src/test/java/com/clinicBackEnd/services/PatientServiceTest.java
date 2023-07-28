package com.clinicBackEnd.services;

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

import com.clinicBackEnd.entities.Patient;
import com.clinicBackEnd.repository.PatientRepository;
import com.clinicBackEnd.service.PatientService;

public class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePatient() {
        Patient patient = new Patient();
        doReturn(patient).when(patientRepository).save(any(Patient.class));

        Patient createdPatient = patientService.createPatient(patient);

        Assertions.assertNotNull(createdPatient);
    }

    @Test
    public void testGetPatientById() {
        Long id = 1L;
        Patient patient = new Patient(id, null, null, 1, null, null);
        doReturn(Optional.of(patient)).when(patientRepository).findById(anyLong());

        Patient result = patientService.getPatientById(id);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(id, result.getId());
    }

    @Test
    public void testGetPatientByIdNotFound() {
        Long id = 1L;
        doReturn(Optional.empty()).when(patientRepository).findById(anyLong());

        Patient result = patientService.getPatientById(id);

        Assertions.assertNull(result);
    }

    @Test
    public void testGetAllPatients() {
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient());
        patients.add(new Patient());
        doReturn(patients).when(patientRepository).findAll();

        List<Patient> result = patientService.getAllPatients();

        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void testUpdatePatient() {
        Long id = 1L;
        Patient existingPatient = new Patient();
        doReturn(Optional.of(existingPatient)).when(patientRepository).findById(anyLong());
        doReturn(existingPatient).when(patientRepository).save(any(Patient.class));

        Patient updatedPatient = new Patient();
        updatedPatient.setFirstName("John");
        updatedPatient.setLastName("Doe");
        updatedPatient.setAge(30);
        updatedPatient.setGender("Male");

        Patient result = patientService.updatePatient(id, updatedPatient);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("John", result.getFirstName());
        Assertions.assertEquals("Doe", result.getLastName());
        Assertions.assertEquals(30, result.getAge());
        Assertions.assertEquals("Male", result.getGender());
    }

    @Test
    public void testUpdatePatientNotFound() {
        Long id = 1L;
        doReturn(Optional.empty()).when(patientRepository).findById(anyLong());

        Patient updatedPatient = new Patient();
        Patient result = patientService.updatePatient(id, updatedPatient);

        Assertions.assertNull(result);
    }

    @Test
    public void testDeletePatient() {
        Long id = 1L;
        doReturn(true).when(patientRepository).existsById(anyLong());

        boolean deleted = patientService.deletePatient(id);

        Assertions.assertTrue(deleted);
    }

    @Test
    public void testDeletePatientNotFound() {
        Long id = 1L;
        doReturn(false).when(patientRepository).existsById(anyLong());

        boolean deleted = patientService.deletePatient(id);

        Assertions.assertFalse(deleted);
    }
}
