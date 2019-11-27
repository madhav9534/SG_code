package com.gs.springbootapp.service;

import java.util.List;

import com.gs.springbootapp.dto.EmployeeDTO;

public interface EmployeeRegService {

	public void createEmployeeReg(EmployeeDTO request);
	
	public List<EmployeeDTO> getEmployeeDetails();
}
