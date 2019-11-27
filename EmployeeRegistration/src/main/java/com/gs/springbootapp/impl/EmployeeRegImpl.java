package com.gs.springbootapp.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gs.springbootapp.dto.EmployeeDTO;
import com.gs.springbootapp.helper.EmployeeRegHelper;
import com.gs.springbootapp.service.EmployeeRegService;

@Service
public class EmployeeRegImpl implements EmployeeRegService {

	@Override
	public void createEmployeeReg(EmployeeDTO request) {
		EmployeeRegHelper.saveEmployeeReg(request);	
	}

	@Override
	public List<EmployeeDTO> getEmployeeDetails() {		
		return EmployeeRegHelper.getEmployeeDetails();
	}

}
