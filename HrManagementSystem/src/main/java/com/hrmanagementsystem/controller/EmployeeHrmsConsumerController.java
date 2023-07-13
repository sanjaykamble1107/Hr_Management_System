package com.hrmanagementsystem.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrmanagementsystem.dto.DepartmentReportDto;
import com.hrmanagementsystem.dto.DesignationReportDto;
import com.hrmanagementsystem.dto.EmployeeDTO;
import com.hrmanagementsystem.dto.EmployeeDesignationProjectionDto;
import com.hrmanagementsystem.dto.EmployeeManagerFromDateDto;
import com.hrmanagementsystem.dto.EmployeesDto;
import com.hrmanagementsystem.dto.ManagerDTO;
import com.hrmanagementsystem.dto.ResponseDto;
import com.hrmanagementsystem.entity.Employees;
import com.hrmanagementsystem.service.EmployeeHrmsConsumerService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/employeehrmsconsumer")
public class EmployeeHrmsConsumerController {
	@Autowired
	private EmployeeHrmsConsumerService employeehrmsconsumer;

	// url = http://localhost:9091/api/v1/employeehrmsconsumer/manager/{fromdate}
	// To Get The List Of Employee Manager By FromDate
	@GetMapping("/manager/{fromdate}")
	public ResponseEntity<List<EmployeeManagerFromDateDto>> getManagers(@PathVariable LocalDate fromdate) {
		List<EmployeeManagerFromDateDto> emplist = employeehrmsconsumer.getManagers(fromdate);
		return new ResponseEntity<List<EmployeeManagerFromDateDto>>(emplist, HttpStatus.OK);
	}

	// url = http://localhost:9091/api/v1/employeehrmsconsumer/designations
	// To Get The List Of Designations
	@GetMapping("/designations")
	public ResponseEntity<List<String>> getDesignations() {
		List<String> emplist = employeehrmsconsumer.getDesignations();
		return new ResponseEntity<List<String>>(emplist, HttpStatus.OK);
	}

	// url = http://localhost:9091/api/v1/employeehrmsconsumer/employee/{empno}
	// To Update The Employee LastName By EmpNo
	@PutMapping("/employee/{empno}")
	public ResponseEntity<ResponseDto> updateEmployeeLastName(@RequestBody EmployeesDto empdto,
			@PathVariable Integer empno) {
		ResponseDto response = employeehrmsconsumer.updateEmployeeLastName(empdto, empno);
		return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
	}

	// url =
	// http://localhost:9091/api/v1/employeehrmsconsumer/employees/titles/{title}
	// To Get The List Of Employees Designation By Title
	// Get empno,name,fromdate,todate,designation
	@GetMapping("/employees/titles/{title}")
	public ResponseEntity<List<EmployeeDesignationProjectionDto>> getEmployeeDesignation(
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "20") Integer size,
			@PathVariable String title) {
		Pageable pageable = PageRequest.of(page, size);
		List<EmployeeDesignationProjectionDto> emplist = employeehrmsconsumer.getEmployeeDesignation(title, pageable);
		return new ResponseEntity<List<EmployeeDesignationProjectionDto>>(emplist, HttpStatus.OK);
	}

	// url = http://localhost:9091/api/v1/employeehrmsconsumer/midageemp
	// To Get The List Of Employees MidAge
	@GetMapping("/midageemp")
	public ResponseEntity<List<Employees>> getMidAgeEmployees(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "20") Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		List<Employees> emplist = employeehrmsconsumer.getMidAgeEmployees(pageable);
		return new ResponseEntity<List<Employees>>(emplist, HttpStatus.OK);
	}

	// url =
	// http://localhost:9091/api/v1/employeehrmsconsumer/employees/department/{deptno}/fromdate/{fromdate}
	// To Get The List Of Employees WorkingSpecific Department By DepartmentNo And
	// FromDate
	@GetMapping("/employees/department/{deptno}/year/{year}")
	public ResponseEntity<List<EmployeeDTO>> getEmployeeWorkingSpecificDept(
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "20") Integer size,
			@PathVariable String deptno, @PathVariable Integer year) {
		Pageable pageable = PageRequest.of(page, size);
		List<EmployeeDTO> emplist = employeehrmsconsumer.getEmployeeWorkingSpecificDept(deptno, year, pageable);
		return new ResponseEntity<List<EmployeeDTO>>(emplist, HttpStatus.OK);
	}

	// url = http://localhost:9091/api/v1/employeehrmsconsumer/manager
	// To Get The List Of EmployeeManager
	@GetMapping("/manager")
	public ResponseEntity<List<ManagerDTO>> getEmployeeManager() {
		List<ManagerDTO> emplist = employeehrmsconsumer.getEmployeeManager();
		return new ResponseEntity<List<ManagerDTO>>(emplist, HttpStatus.OK);
	}

	// url = http://localhost:9091/api/v1/employeehrmsconsumer/designation/details
	// To Get The List Of DesignationDetails
	@GetMapping("/designation/details")
	public ResponseEntity<List<DesignationReportDto>> getDesignationDetails() {
		List<DesignationReportDto> emplist = employeehrmsconsumer.getDesignationDetails();
		return new ResponseEntity<List<DesignationReportDto>>(emplist, HttpStatus.OK);
	}

	// url = http://localhost:9091/api/v1/employeehrmsconsumer/department
	// To Get The List Of getDepartmentDetails
	@GetMapping("/department")
	public ResponseEntity<List<DepartmentReportDto>> getDepartmentDetails() {
		List<DepartmentReportDto> emplist = employeehrmsconsumer.getDepartmentDetails();
		return new ResponseEntity<List<DepartmentReportDto>>(emplist, HttpStatus.OK);
	}

}
