package com.gs.springbootapp.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gs.springbootapp.dto.EmployeeDTO;

public class EmployeeRegHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeRegHelper.class);

	public static final String CSV_FILE_PATH = "src/main/resources/CSV/employee.csv";
	public static final String CSV_INPUT_STREAM_FILE_PATH = "/CSV/employee.csv";
	public static final String DATE_FORMATE = "E MMM dd HH:mm:ss Z yyyy";

	
	/*This method is try to save the Employee details in csv file 
	 * which is located in src/main/resources/CSV/employee.csv	 
	 * */
	public static void saveEmployeeReg(EmployeeDTO request) {

		try {

			InputStream inputStream = EmployeeDTO.class.getResourceAsStream(CSV_INPUT_STREAM_FILE_PATH);
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

			FileWriter fr = new FileWriter(new File(CSV_FILE_PATH));

			BufferedWriter bw = new BufferedWriter(fr);

			BufferedWriter writer = new BufferedWriter(bw);

			String line = null;
			while ((line = br.readLine()) != null) {
				writer.write(line);
				writer.newLine();
			}

			writer.write(request.getFirstName());
			writer.write(",");
			writer.write(request.getLastName());
			writer.write(",");
			writer.write(request.getGender());
			writer.write(",");
			writer.write(request.getDob().toString());
			writer.write(",");
			writer.write(request.getDepartment());
			LOGGER.info("Details is sucessfully created");
			
			
			writer.flush();
			inputStream.close();
			br.close();
			writer.close();
			bw.close();
			fr.close();
			LOGGER.info("file connection is sucessfully close");

		} catch (Exception e) {
			LOGGER.error("Error in creating Employee Details" + e);
		}
	}

	/*
	 * This method is try to fetch the Employee details from CSV file
	 */
	public static List<EmployeeDTO> getEmployeeDetails() {
		List<EmployeeDTO> empDetails = new ArrayList<>();
		try (InputStream inputStream = EmployeeDTO.class.getResourceAsStream(CSV_INPUT_STREAM_FILE_PATH);
				BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));) {
			String line = "";

			while ((line = br.readLine()) != null) {

				String[] str = line.split(",");

				if (str.length > 0) {
					EmployeeDTO emp = new EmployeeDTO();
					emp.setFirstName(str[0]);
					emp.setLastName(str[1]);
					emp.setGender(str[2]);
					emp.setDob(covertToDate(str[3]));
					emp.setDepartment(str[4]);
					empDetails.add(emp);
				}
			}
			LOGGER.info("Details is sucessfully loaded");
			br.close();
			LOGGER.info("file connection is sucessfully close");
		} catch (Exception e) {
			LOGGER.error("Error in loading Employee Details" + e);
		}
		if (empDetails != null) {
			empDetails = sortEmp(empDetails);
		}
		return empDetails;

	}

	private static List<EmployeeDTO> sortEmp(List<EmployeeDTO> empDetails) {
		return  empDetails.stream().sorted((EmployeeDTO obj1,EmployeeDTO obj2) -> obj1.getFirstName().compareTo(obj2.getFirstName()))
				.collect(Collectors.toList());

	}

	private static Date covertToDate(String stringDate) {
		SimpleDateFormat formatter1 = new SimpleDateFormat(DATE_FORMATE);
		try {
			return formatter1.parse(stringDate);
		} catch (ParseException e) {
			LOGGER.error("Error in date conversion" + e);
		}
		;
		return null;
	}

}
