package com.clinicBackEnd.entities;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AppointmentTest {

    @Test
    public void testAppointmentEntity() {
        Patient patient = new Patient(1L, "John", "Doe", 30, "Male", null);
        Procedures procedures = new Procedures(1L, "Dental Checkup", "Regular dental checkup", 100.0, null);

        Appointment appointment = new Appointment(1L, LocalDateTime.now(), "Location 1", patient, procedures);

        assertThat(appointment.getId()).isEqualTo(1L);
        assertThat(appointment.getDateTime()).isNotNull();
        assertThat(appointment.getLocation()).isEqualTo("Location 1");
        assertThat(appointment.getPatient()).isEqualTo(patient);
        assertThat(appointment.getProcedures()).isEqualTo(procedures);
    }
}
