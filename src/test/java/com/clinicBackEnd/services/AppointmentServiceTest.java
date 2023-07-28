package com.clinicBackEnd.services;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.clinicBackEnd.entities.Appointment;
import com.clinicBackEnd.repository.AppointmentRepository;
import com.clinicBackEnd.service.AppointmentService;

@SpringBootTest
public class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAppointment() {
        Appointment appointment = new Appointment();
        appointment.setDateTime(LocalDateTime.now());
        appointment.setLocation("Clinic A");

        when(appointmentRepository.save(appointment)).thenReturn(appointment);

        Appointment createdAppointment = appointmentService.createAppointment(appointment);

        assertNotNull(createdAppointment);
        assertEquals(appointment.getDateTime(), createdAppointment.getDateTime());
        assertEquals(appointment.getLocation(), createdAppointment.getLocation());

        verify(appointmentRepository, times(1)).save(appointment);
    }

    @Test
    public void testGetAppointmentById() {
        Long appointmentId = 1L;
        Appointment appointment = new Appointment();
        appointment.setId(appointmentId);
        appointment.setDateTime(LocalDateTime.now());
        appointment.setLocation("Clinic A");

        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.of(appointment));

        Appointment foundAppointment = appointmentService.getAppointmentById(appointmentId);

        assertNotNull(foundAppointment);
        assertEquals(appointmentId, foundAppointment.getId());
        assertEquals(appointment.getDateTime(), foundAppointment.getDateTime());
        assertEquals(appointment.getLocation(), foundAppointment.getLocation());

        verify(appointmentRepository, times(1)).findById(appointmentId);
    }

    @Test
    public void testGetAllAppointments() {
        List<Appointment> appointmentList = new ArrayList<>();
        appointmentList.add(new Appointment(1L, LocalDateTime.now(), "Clinic A", null, null));
        appointmentList.add(new Appointment(2L, LocalDateTime.now(), "Clinic B", null, null));

        when(appointmentRepository.findAll()).thenReturn(appointmentList);

        List<Appointment> allAppointments = appointmentService.getAllAppointments();

        assertNotNull(allAppointments);
        assertEquals(2, allAppointments.size());

        verify(appointmentRepository, times(1)).findAll();
    }

    @Test
    public void testUpdateAppointment() {
        Long appointmentId = 1L;
        Appointment existingAppointment = new Appointment();
        existingAppointment.setId(appointmentId);
        existingAppointment.setDateTime(LocalDateTime.now());
        existingAppointment.setLocation("Clinic A");

        Appointment updatedAppointment = new Appointment();
        updatedAppointment.setDateTime(LocalDateTime.now().plusDays(1));
        updatedAppointment.setLocation("Clinic B");

        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.of(existingAppointment));
        when(appointmentRepository.save(existingAppointment)).thenReturn(existingAppointment);

        Appointment resultAppointment = appointmentService.updateAppointment(appointmentId, updatedAppointment);

        assertNotNull(resultAppointment);
        assertEquals(appointmentId, resultAppointment.getId());
        assertEquals(updatedAppointment.getDateTime(), resultAppointment.getDateTime());
        assertEquals(updatedAppointment.getLocation(), resultAppointment.getLocation());

        verify(appointmentRepository, times(1)).findById(appointmentId);
        verify(appointmentRepository, times(1)).save(existingAppointment);
    }

    @Test
    public void testDeleteAppointment() {
        Long appointmentId = 1L;

        when(appointmentRepository.existsById(appointmentId)).thenReturn(true);

        boolean deleted = appointmentService.deleteAppointment(appointmentId);

        assertTrue(deleted);

        verify(appointmentRepository, times(1)).existsById(appointmentId);
        verify(appointmentRepository, times(1)).deleteById(appointmentId);
    }

    @Test
    public void testDeleteNonExistentAppointment() {
        Long appointmentId = 1L;

        when(appointmentRepository.existsById(appointmentId)).thenReturn(false);

        boolean deleted = appointmentService.deleteAppointment(appointmentId);

        assertFalse(deleted);

        verify(appointmentRepository, times(1)).existsById(appointmentId);
        verify(appointmentRepository, never()).deleteById(appointmentId);
    }
}
