package com.clinicBackEnd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinicBackEnd.entities.Procedures;
import com.clinicBackEnd.repository.ProceduresRepository;

@Service
public class ProceduresService {

    @Autowired
    private ProceduresRepository proceduresRepository;

    public Procedures createProcedures(Procedures procedures) {
        return proceduresRepository.save(procedures);
    }

    public Procedures getProceduresById(Long id) {
        return proceduresRepository.findById(id).orElse(null);
    }

    public List<Procedures> getAllProcedures() {
        return proceduresRepository.findAll();
    }

    public Procedures updateProcedures(Long id, Procedures updatedProcedures) {
        Procedures procedures = getProceduresById(id);
        if (procedures != null) {
            procedures.setName(updatedProcedures.getName());
            procedures.setDescription(updatedProcedures.getDescription());
            procedures.setPrice(updatedProcedures.getPrice());
            procedures.setAppointment(updatedProcedures.getAppointment());
            return proceduresRepository.save(procedures);
        }
        return null;
    }

    public boolean deleteProcedures(Long id) {
        if (proceduresRepository.existsById(id)) {
            proceduresRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

