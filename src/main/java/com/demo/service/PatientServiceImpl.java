package com.demo.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.demo.common.Constants;
import com.demo.dao.PatientDao;
import com.demo.exception.ServiceApplicationException;
import com.demo.model.Patient;
import com.demo.vo.ResponseVO;

@Component
public class PatientServiceImpl implements PatientService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PatientDao patientDao;

	@Override
	public ResponseVO create(Patient patient) throws ServiceApplicationException {
		ResponseVO vo = new ResponseVO();
		try {
			vo.setResponse(patientDao.save(patient));
		} catch (DataIntegrityViolationException e) {
			logger.debug(e.getMessage());
			throw new ServiceApplicationException(HttpStatus.BAD_REQUEST, Constants.PATIENT_ID_DUPLICATE);
		}
		return vo;
	}

	@Override
	public ResponseVO update(Patient patient) throws ServiceApplicationException {
		ResponseVO vo = new ResponseVO();
		Optional<Patient> patientOp = patientDao.findById(patient.getId());
		Patient patientDB = null;
		if (patientOp.isPresent()) {
			patientDB = patientOp.get();
			patientDB.setName(patient.getName());
			patientDB.setDob(patient.getDob());
			patientDB.setGender(patient.getGender());
			patientDB.setPatientId(patient.getPatientId());
			patientDB.setDescription(patient.getDescription());
			try {
				vo.setResponse(patientDao.save(patientDB));
			} catch (DataIntegrityViolationException e) {
				logger.debug(e.getMessage());
				throw new ServiceApplicationException(HttpStatus.BAD_REQUEST, Constants.INSTITUTION_NAME_DUPLICATE);
			}
		} else {
			logger.debug("Invalid Patient ID: " + patient.getId());
			throw new ServiceApplicationException(HttpStatus.BAD_REQUEST, Constants.INVALID_ID);
		}
		return vo;
	}

	@Override
	public ResponseVO getList() {
		ResponseVO vo = new ResponseVO();
		vo.setResponse(patientDao.findAll());
		return vo;
	}

}
