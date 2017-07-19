package com.demo.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.model.Institution;

@Transactional
public interface InstitutionDao extends CrudRepository<Institution, Long> {

}
