package com.clinicBackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinicBackEnd.entities.Procedures;

@Repository
public interface ProceduresRepository extends JpaRepository<Procedures, Long> {

}
