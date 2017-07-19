package com.demo.service;

import com.demo.exception.ServiceApplicationException;
import com.demo.model.Examination;
import com.demo.vo.ResponseVO;

public interface ExaminationService {

	ResponseVO create(Examination examination) throws ServiceApplicationException;

	ResponseVO update(Examination examination) throws ServiceApplicationException;

	ResponseVO getList(long patientId);
}
