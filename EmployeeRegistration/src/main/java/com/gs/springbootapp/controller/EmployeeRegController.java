package com.gs.springbootapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gs.springbootapp.dto.EmployeeDTO;
import com.gs.springbootapp.service.EmployeeRegService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class EmployeeRegController {

	@Autowired	
	EmployeeRegService employeeRegService;
	
	@RequestMapping(value="/createemployee", method = RequestMethod.POST)
	public String createEmployeeReg(@RequestBody EmployeeDTO request){
		employeeRegService.createEmployeeReg(request);		
		return "returns";
		
	}
	
	@RequestMapping(value="/getemployee", method = RequestMethod.GET)
	public List<EmployeeDTO> getEmployeeRegDetails(){				
		return employeeRegService.getEmployeeDetails();
		
	}
}
