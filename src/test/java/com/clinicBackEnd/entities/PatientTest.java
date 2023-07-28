package com.clinicBackEnd.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PatientTest {

    private Patient patient;

    @BeforeEach
    public void setUp() {
        patient = new Patient();
    }

    @Test
    public void testId() {
        Long id = 1L;
        patient.setId(id);
        Assertions.assertEquals(id, patient.getId());
    }

    @Test
    public void testFirstName() {
        String firstName = "John";
        patient.setFirstName(firstName);
        Assertions.assertEquals(firstName, patient.getFirstName());
    }

    @Test
    public void testLastName() {
        String lastName = "Doe";
        patient.setLastName(lastName);
        Assertions.assertEquals(lastName, patient.getLastName());
    }

    @Test
    public void testAge() {
        int age = 30;
        patient.setAge(age);
        Assertions.assertEquals(age, patient.getAge());
    }

    @Test
    public void testGender() {
        String gender = "Male";
        patient.setGender(gender);
        Assertions.assertEquals(gender, patient.getGender());
    }

    @Test
    public void testAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        Appointment appointment1 = new Appointment();
        Appointment appointment2 = new Appointment();

        appointments.add(appointment1);
        appointments.add(appointment2);

        patient.setAppointments(appointments);

        Assertions.assertEquals(2, patient.getAppointments().size());
        Assertions.assertTrue(patient.getAppointments().contains(appointment1));
        Assertions.assertTrue(patient.getAppointments().contains(appointment2));
    }
}
