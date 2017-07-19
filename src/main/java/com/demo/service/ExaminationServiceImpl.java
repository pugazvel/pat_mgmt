package com.demo.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.demo.common.Constants;
import com.demo.common.EXAM_STATUS;
import com.demo.dao.ExaminationDao;
import com.demo.exception.ServiceApplicationException;
import com.demo.model.Examination;
import com.demo.vo.ResponseVO;

@Component
public class ExaminationServiceImpl implements ExaminationService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ExaminationDao examinationDao;

	@Override
	public ResponseVO create(Examination examination) throws ServiceApplicationException {
		ResponseVO vo = new ResponseVO();
		examination.setStatus(EXAM_STATUS.NEW.getId());
		vo.setResponse(examinationDao.save(examination));
		return vo;
	}

	@Override
	public ResponseVO update(Examination examination) throws ServiceApplicationException {
		ResponseVO vo = new ResponseVO();
		Optional<Examination> objOp = examinationDao.findById(examination.getId());
		Examination objDb = null;
		if (objOp.isPresent()) {
			objDb = objOp.get();
			objDb.setExamName(examination.getExamName());
			objDb.setExamDate(examination.getExamDate());
			objDb.setPatientId(examination.getPatientId());
			objDb.setHeight(examination.getHeight());
			objDb.setWeight(examination.getWeight());
			objDb.setDescription(examination.getDescription());
			vo.setResponse(examinationDao.save(objDb));
		} else {
			logger.debug("Invalid Examination ID: " + examination.getId());
			throw new ServiceApplicationException(HttpStatus.BAD_REQUEST, Constants.INVALID_ID);
		}
		return vo;
	}

	@Override
	public ResponseVO getList(long patientId) {
		ResponseVO vo = new ResponseVO();
		vo.setResponse(examinationDao.findByPatientId(patientId));
		return vo;
	}

}
