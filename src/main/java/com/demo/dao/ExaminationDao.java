package com.demo.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.model.Examination;

@Transactional
public interface ExaminationDao extends CrudRepository<Examination, Long> {

	List<Examination> findByPatientId(long patientId);
}
