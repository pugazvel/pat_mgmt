package com.demo.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.demo.common.Constants;
import com.demo.dao.InstitutionDao;
import com.demo.exception.ServiceApplicationException;
import com.demo.model.Institution;
import com.demo.vo.ResponseVO;

@Component
public class InstitutionServiceImpl implements InstitutionService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private InstitutionDao institutionDao;

	@Override
	public ResponseVO create(Institution institution) throws ServiceApplicationException {
		ResponseVO vo = new ResponseVO();
		try {
			vo.setResponse(institutionDao.save(institution));
		} catch (DataIntegrityViolationException e) {
			logger.debug(e.getMessage());
			throw new ServiceApplicationException(HttpStatus.BAD_REQUEST, Constants.INSTITUTION_NAME_DUPLICATE);
		}
		return vo;
	}

	@Override
	public ResponseVO update(Institution institution) throws ServiceApplicationException {
		ResponseVO vo = new ResponseVO();
		Optional<Institution> instDb = institutionDao.findById(institution.getId());
		Institution inst = null;
		if (instDb.isPresent()) {
			inst = instDb.get();
			inst.setName(institution.getName());
			inst.setDescription(institution.getDescription());
			try {
				vo.setResponse(institutionDao.save(inst));
			} catch (DataIntegrityViolationException e) {
				logger.debug(e.getMessage());
				throw new ServiceApplicationException(HttpStatus.BAD_REQUEST, Constants.INSTITUTION_NAME_DUPLICATE);
			}
		} else {
			logger.debug("Invalid Institution ID: " + institution.getId());
			throw new ServiceApplicationException(HttpStatus.BAD_REQUEST, Constants.INVALID_ID);
		}
		return vo;
	}

	@Override
	public ResponseVO getList() {
		ResponseVO vo = new ResponseVO();
		vo.setResponse(institutionDao.findAll());
		return vo;
	}

}
