package com.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.exception.ServiceApplicationException;
import com.demo.model.Institution;
import com.demo.service.InstitutionService;
import com.demo.vo.ResponseVO;

@Controller
@RequestMapping("/institution")
public class InstitutionController {

	@Autowired
	private InstitutionService institutionService;

	/**
	 * POST /create --> Create a new institution and save it in the database.
	 * 
	 * @throws ServiceApplicationException
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/create")
	@ResponseBody
	public ResponseVO create(@Valid @RequestBody Institution institution) throws ServiceApplicationException {
		return institutionService.create(institution);
	}

	/**
	 * POST /update --> Update the institution in the
	 * database having the passed id.
	 * 
	 * @throws ServiceApplicationException
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/update")
	@ResponseBody
	public ResponseVO update(@RequestBody Institution institution) throws ServiceApplicationException {
		return institutionService.update(institution);
	}

	/**
	 * POST /fetch --> get list of institution.
	 * 
	 * @throws ServiceApplicationException
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/fetch")
	@ResponseBody
	public ResponseVO getList() throws ServiceApplicationException {
		return institutionService.getList();
	}

}
