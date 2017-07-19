package com.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.exception.ServiceApplicationException;
import com.demo.model.Examination;
import com.demo.service.ExaminationService;
import com.demo.vo.ResponseVO;

@Controller
@RequestMapping("/exam")
public class ExaminationController {

	@Autowired
	private ExaminationService examinationService;

	/**
	 * POST /create --> Create a new exam and save it in the database.
	 * 
	 * @throws ServiceApplicationException
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/create")
	@ResponseBody
	public ResponseVO create(@Valid @RequestBody Examination examination) throws ServiceApplicationException {
		return examinationService.create(examination);
	}

	/**
	 * POST /update --> Update Exam into database having the passed id.
	 * 
	 * @throws ServiceApplicationException
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/update")
	@ResponseBody
	public ResponseVO update(@RequestBody Examination examination) throws ServiceApplicationException {
		return examinationService.update(examination);
	}

	/**
	 * POST /fetch --> get list of patient.
	 * 
	 * @throws ServiceApplicationException
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/fetch")
	@ResponseBody
	public ResponseVO getList(Long patientId) throws ServiceApplicationException {
		return examinationService.getList(patientId);
	}

}
