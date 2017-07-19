package com.demo.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.model.Patient;

@Transactional
public interface PatientDao extends CrudRepository<Patient, Long> {

	List<Patient> findByInstitutionId(Long institutionId);
}
