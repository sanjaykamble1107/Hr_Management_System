package com.hrmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrmanagementsystem.dto.DepartmentsDto;
import com.hrmanagementsystem.serviceimpl.DepartmentServiceImpl;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/departments")
public class DepartmentsController {
	@Autowired
	private DepartmentServiceImpl departmentservice;

	// url = http://localhost:9091/api/v1/departments/adddepartment
	// To Add A new department
	@PostMapping("/adddepartment")
	public ResponseEntity<String> saveDepartment(@Valid @RequestBody DepartmentsDto departmentdto) {
		String response = departmentservice.addDepartment(departmentdto);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	// url = http://localhost:9091/api/v1/departments
	// To Fetch The List Of AllDepartments
	@GetMapping()
	public ResponseEntity<List<DepartmentsDto>> getAllDepartments() {
		List<DepartmentsDto> departmentsList = departmentservice.getAllDepartments();
		return new ResponseEntity<List<DepartmentsDto>>(departmentsList, HttpStatus.OK);
	}

	// url = http://localhost:9091/api/v1/departments/{deptno}
	// To Search The Department By DepartmentNo
	@GetMapping("/{deptno}")
	public ResponseEntity<DepartmentsDto> getDepartmentByNo(@PathVariable String deptno) {
		DepartmentsDto departmentByNo = departmentservice.getDepartmentByNo(deptno);
		return new ResponseEntity<DepartmentsDto>(departmentByNo, HttpStatus.OK);

	}

	// url = http://localhost:9091/api/v1/departments/name/{deptname}
	// To Search The Department By DepartmentName
	@GetMapping("/name/{deptname}")
	public ResponseEntity<DepartmentsDto> getDepartmentByName(@PathVariable String deptname) {
		DepartmentsDto departmentByName = departmentservice.getDepartmentByName(deptname);
		return new ResponseEntity<DepartmentsDto>(departmentByName, HttpStatus.OK);

	}

	// url = http://localhost:9091/api/v1/departments/{deptno}
	// To Update The Department By DepartmentNo
	@PutMapping("/{deptno}")
	public ResponseEntity<String> updateDepartmentByNo(@RequestBody DepartmentsDto deptdto,
			@PathVariable String deptno) {
		String response = departmentservice.updateDepartmentByNo(deptdto, deptno);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	// url = http://localhost:9091/api/v1/departments/name/{deptname}
	// To Update The Department By DepartmentName
	@PutMapping("/name/{deptname}")
	public ResponseEntity<String> updateDepartmentByName(@Valid @RequestBody DepartmentsDto deptdto,
			@PathVariable String deptname) {
		String response = departmentservice.updateDepartmentByName(deptdto, deptname);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
}
