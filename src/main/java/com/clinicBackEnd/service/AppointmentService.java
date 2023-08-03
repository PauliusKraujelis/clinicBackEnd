package com.clinicBackEnd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinicBackEnd.entities.Appointment;
import com.clinicBackEnd.entities.Patient;
import com.clinicBackEnd.repository.AppointmentRepository;
import com.clinicBackEnd.repository.PatientRepository;

@Service
public class AppointmentService {

	@Autowired
	private PatientService patientService;

	@Autowired
	private AppointmentRepository appointmentRepository;

	public Appointment createAppointment(Appointment appointment) {
		return appointmentRepository.save(appointment);
	}

	public Appointment getAppointmentById(Long id) {
		return appointmentRepository.findById(id).orElse(null);
	}

	public List<Appointment> getAllAppointments() {
		return appointmentRepository.findAll();
	}

	public Appointment updateAppointment(Long id, Appointment updatedAppointment) {
		Appointment appointment = getAppointmentById(id);
		if (appointment != null) {
			appointment.setDateTime(updatedAppointment.getDateTime());
			appointment.setLocation(updatedAppointment.getLocation());

			Long patientId = updatedAppointment.getPatientId();
			if (patientId != null) {
				Patient patient = patientService.getPatientById(patientId);
				if (patient != null) {
					appointment.setPatient(patient);
				} else {

				}
			}

			return appointmentRepository.save(appointment);
		}
		return null;
	}

	public boolean deleteAppointment(Long id) {
		if (appointmentRepository.existsById(id)) {
			appointmentRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
