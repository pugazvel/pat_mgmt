package com.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.exception.ServiceApplicationException;
import com.demo.model.Patient;
import com.demo.service.PatientService;
import com.demo.vo.ResponseVO;

@Controller
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	private PatientService patientService;

	/**
	 * POST /create --> Create a new patient and save it in the database.
	 * 
	 * @throws ServiceApplicationException
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/create")
	@ResponseBody
	public ResponseVO create(@Valid @RequestBody Patient patient) throws ServiceApplicationException {
		return patientService.create(patient);
	}

	/**
	 * POST /update --> Update Patient into
	 * database having the passed id.
	 * 
	 * @throws ServiceApplicationException
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/update")
	@ResponseBody
	public ResponseVO update(@RequestBody Patient patient) throws ServiceApplicationException {
		return patientService.update(patient);
	}

	/**
	 * POST /fetch --> get list of patient.
	 * 
	 * @throws ServiceApplicationException
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/fetch")
	@ResponseBody
	public ResponseVO getList() throws ServiceApplicationException {
		return patientService.getList();
	}

}
