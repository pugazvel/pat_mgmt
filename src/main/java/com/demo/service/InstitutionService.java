package com.demo.service;

import com.demo.exception.ServiceApplicationException;
import com.demo.model.Institution;
import com.demo.vo.ResponseVO;

public interface InstitutionService {

	ResponseVO create(Institution institution) throws ServiceApplicationException;

	ResponseVO update(Institution institution) throws ServiceApplicationException;

	ResponseVO getList();
}
