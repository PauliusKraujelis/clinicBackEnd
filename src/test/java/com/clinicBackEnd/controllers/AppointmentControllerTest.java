package com.clinicBackEnd.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.clinicBackEnd.controller.AppointmentController;
import com.clinicBackEnd.entities.Appointment;
import com.clinicBackEnd.service.AppointmentService;

public class AppointmentControllerTest {

    @Mock
    private AppointmentService appointmentService;

    @InjectMocks
    private AppointmentController appointmentController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAppointment() {
        Appointment appointment = new Appointment();
        appointment.setId(1L);

        when(appointmentService.createAppointment(any(Appointment.class))).thenReturn(appointment);

        ResponseEntity<Appointment> response = appointmentController.createAppointment(appointment);

        assert response.getStatusCode() == HttpStatus.CREATED;
        assert response.getBody().getId() == 1L;
    }

    @Test
    public void testGetAppointmentById() {
        // Mock data
        Appointment appointment = new Appointment();
        appointment.setId(1L);

        when(appointmentService.getAppointmentById(1L)).thenReturn(appointment);

        ResponseEntity<Appointment> response = appointmentController.getAppointmentById(1L);

        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody().getId() == 1L;
    }

    @Test
    public void testGetAppointmentById_NotFound() {
        when(appointmentService.getAppointmentById(1L)).thenReturn(null);

        ResponseEntity<Appointment> response = appointmentController.getAppointmentById(1L);

        assert response.getStatusCode() == HttpStatus.NOT_FOUND;
    }

    @Test
    public void testGetAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(new Appointment());
        appointments.add(new Appointment());

        when(appointmentService.getAllAppointments()).thenReturn(appointments);

        ResponseEntity<List<Appointment>> response = appointmentController.getAllAppointments();

        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody().size() == 2;
    }

    @Test
    public void testUpdateAppointment() {
        Appointment appointment = new Appointment();
        appointment.setId(1L);

        when(appointmentService.updateAppointment(1L, appointment)).thenReturn(appointment);

        ResponseEntity<Appointment> response = appointmentController.updateAppointment(1L, appointment);

        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody().getId() == 1L;
    }

    @Test
    public void testUpdateAppointment_NotFound() {
        Appointment appointment = new Appointment();
        appointment.setId(1L);

        when(appointmentService.updateAppointment(1L, appointment)).thenReturn(null);

        ResponseEntity<Appointment> response = appointmentController.updateAppointment(1L, appointment);

        assert response.getStatusCode() == HttpStatus.NOT_FOUND;
    }

    @Test
    public void testDeleteAppointment() {
        when(appointmentService.deleteAppointment(1L)).thenReturn(true);

        ResponseEntity<Void> response = appointmentController.deleteAppointment(1L);

        assert response.getStatusCode() == HttpStatus.NO_CONTENT;
    }

    @Test
    public void testDeleteAppointment_NotFound() {
        when(appointmentService.deleteAppointment(1L)).thenReturn(false);

        ResponseEntity<Void> response = appointmentController.deleteAppointment(1L);

        assert response.getStatusCode() == HttpStatus.NOT_FOUND;
    }
}
