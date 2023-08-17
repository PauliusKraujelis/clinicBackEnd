package com.clinicBackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinicBackEnd.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	public UserEntity findByEmail(String email);
}
