package com.clinicBackEnd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinicBackEnd.entities.Procedures;
import com.clinicBackEnd.service.ProceduresService;

@RestController
@RequestMapping("/api/procedures")
public class ProceduresController {

    @Autowired
    private ProceduresService proceduresService;

    @PostMapping
    public ResponseEntity<Procedures> createProcedures(@RequestBody Procedures procedures) {
        Procedures createdProcedures = proceduresService.createProcedures(procedures);
        return new ResponseEntity<>(createdProcedures, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Procedures> getProceduresById(@PathVariable Long id) {
        Procedures procedures = proceduresService.getProceduresById(id);
        if (procedures != null) {
            return new ResponseEntity<>(procedures, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Procedures>> getAllProcedures() {
        List<Procedures> proceduresList = proceduresService.getAllProcedures();
        return new ResponseEntity<>(proceduresList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Procedures> updateProcedures(@PathVariable Long id, @RequestBody Procedures procedures) {
        Procedures updatedProcedures = proceduresService.updateProcedures(id, procedures);
        if (updatedProcedures != null) {
            return new ResponseEntity<>(updatedProcedures, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProcedures(@PathVariable Long id) {
        boolean deleted = proceduresService.deleteProcedures(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
