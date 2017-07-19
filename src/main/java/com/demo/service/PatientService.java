package com.demo.service;

import com.demo.exception.ServiceApplicationException;
import com.demo.model.Patient;
import com.demo.vo.ResponseVO;

public interface PatientService {

	ResponseVO create(Patient patient) throws ServiceApplicationException;

	ResponseVO update(Patient patient) throws ServiceApplicationException;

	ResponseVO getList();
}
