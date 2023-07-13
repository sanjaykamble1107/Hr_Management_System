package com.hrmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrmanagementsystem.dto.DepartmentEmployeeDto;
import com.hrmanagementsystem.dto.DepartmentManagerDto;
import com.hrmanagementsystem.dto.DepartmentsDto;
import com.hrmanagementsystem.dto.EmployeesDto;
import com.hrmanagementsystem.dto.GetEmployeeJoinedAfterHireDate;
import com.hrmanagementsystem.dto.RequestDto;
import com.hrmanagementsystem.dto.ResponseDto;
import com.hrmanagementsystem.dto.TitlesDto;
import com.hrmanagementsystem.entity.Gender;
import com.hrmanagementsystem.service.AdminHrmsConsumerService;
import com.hrmanagementsystem.service.EmployeeService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/adminhrmsconsumer")
public class AdminHrmsConsumerController {
	@Autowired
	private AdminHrmsConsumerService adminhrmsconsumerservice;

	// Specially for using the method from EmployeeService public List<EmployeesDto>
	// getEmployeeByGender(Gender gender)
	@Autowired
	private EmployeeService employeeservice;

	

	// url = http://localhost:9091/api/v1/adminhrmsconsumer/employee
	// To Add New Employee
	@PostMapping("/employee")
	public ResponseEntity<ResponseDto> addEmployee(@Valid @RequestBody EmployeesDto dto) {
		ResponseDto response = adminhrmsconsumerservice.addEmployee(dto);
		return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
	}
	
	// url = http://localhost:9091/api/v1/adminhrmsconsumer/employees/{noofyear}
	// To Get The List Of Employees Joined After HireDate
	@GetMapping("/employees/{noofyear}")
	public ResponseEntity<List<GetEmployeeJoinedAfterHireDate>> getEmployeeByLastNYear(@PathVariable Integer noofyear) {
		List<GetEmployeeJoinedAfterHireDate> employeeByLastNYear = adminhrmsconsumerservice
				.getEmployeeByLastNYear(noofyear);
		return new ResponseEntity<List<GetEmployeeJoinedAfterHireDate>>(employeeByLastNYear, HttpStatus.OK);
	}
	
	// url = http://localhost:9091/api/v1/adminhrmsconsumer/employees/{empno}
	// To delete the Employee by empNo
	@DeleteMapping("/employee/{empno}")
	public ResponseEntity<ResponseDto> deleteEmployee(@PathVariable Integer empno) {
		ResponseDto response = adminhrmsconsumerservice.deleteEmployee(empno);
		return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
	}
	
	
	// url = http://localhost:9091/api/v1/adminhrmsconsumer/employees/count/{noofyear}
	// To Get The Count Of Employees By Last Ten Years
	@GetMapping("/employees/count/{noofyear}")
	public ResponseEntity<Integer> getCountEmployeeByLastTenYear(@PathVariable Integer noofyear) {
		Integer count = adminhrmsconsumerservice.getCountEmployeeByLastNYear(noofyear);
		return new ResponseEntity<Integer>(count, HttpStatus.OK);
	}

	// url = http://localhost:9091/api/v1/adminhrmsconsumer/employees/year/{year}
	// To Get A List Of Employees By A joining Year
	@GetMapping("/employees/year/{year}")
	public ResponseEntity<List<EmployeesDto>> getEmployeeListByNJoinYear(@PathVariable Integer year) {
		List<EmployeesDto> employeejoinedfromyear = adminhrmsconsumerservice.getEmployeeListByNJoinYear(year);
		return new ResponseEntity<List<EmployeesDto>>(employeejoinedfromyear, HttpStatus.OK);
	}


	// url = http://localhost:9091/api/v1/adminhrmsconsumer/employees/count/year/{year}
	// To get the Count Of Employees By A Joining Year
	@GetMapping("/employees/count/year/{year}")
	public ResponseEntity<Integer> getEmployeeCountByNJoinYear(@PathVariable Integer year) {
		Integer count = adminhrmsconsumerservice.getEmployeeCountByNJoinYear(year);
		return new ResponseEntity<Integer>(count, HttpStatus.OK);
	}


	// url=http://localhost:9091/api/v1/adminhrmsconsumer/assigndept
	// To add employee to specific department
	@PostMapping("/assigndept")
	public ResponseEntity<ResponseDto> saveDeptEmp(@RequestBody DepartmentEmployeeDto dto) {
		ResponseDto response = adminhrmsconsumerservice.saveDeptEmp(dto);
		return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
	}

	// url = http://localhost:9091/api/v1/adminhrmsconsumer/assignmgr
	// To add a Department Manager
	@PostMapping("/assignmgr")
	public ResponseEntity<ResponseDto> saveDeptMgr(@Valid @RequestBody DepartmentManagerDto dto) {
		ResponseDto response = adminhrmsconsumerservice.saveDeptMgr(dto);
		return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
	}

	// url = http://localhost:9091/api/v1/adminhrmsconsumer/assigntitle
	// To assign title to employee
	@PostMapping("/assigntitle")
	public ResponseEntity<ResponseDto> assignTitle(@Valid @RequestBody TitlesDto dto) {
		ResponseDto response = adminhrmsconsumerservice.assignTitle(dto);
		return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
	}

	
	// url = http://localhost:9091/api/v1/adminhrmsconsumer/department
	// To Add A Department
	@PostMapping("/department")
	public ResponseEntity<ResponseDto> addDepartment(@Valid @RequestBody DepartmentsDto dto) {
		ResponseDto response = adminhrmsconsumerservice.addDepartment(dto);
		return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
	}

	// url = http://localhost:9091/api/v1/adminhrmsconsumer/employee/experience/{noofyear}
	// To Get The List Of Employees Experience By No Of Year
	@GetMapping("/employee/experience/{noofyear}")
	public ResponseEntity<List<EmployeesDto>> getExperiencedEmployeeByYear(@PathVariable Integer noofyear) {
		List<EmployeesDto> emplist = adminhrmsconsumerservice.getExperiencedEmployeeByYear(noofyear);
		return new ResponseEntity<List<EmployeesDto>>(emplist, HttpStatus.OK);
	}

	
	// url = http://localhost:9091/api/v1/adminhrmsconsumer/employee/gender/{gender}/count
	// To Get The Count Of Employee By Gender
	@GetMapping("/employee/gender/{gender}/count")
	public ResponseEntity<Integer> getEmployeeCountByGender(@PathVariable String gender) {
		Integer count = adminhrmsconsumerservice.getEmployeeCountByGender(gender);
		return new ResponseEntity<Integer>(count, HttpStatus.OK);
	}


	// url = http://localhost:9091/api/v1/adminhrmsconsumer/employee/gender/{gender}
	// To Get The List Of Employee By Gender
	// This method we have used directly from EmployeeService
	@GetMapping("/employee/gender/{gender}")
	public ResponseEntity<List<EmployeesDto>> getEmployeeByGender(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "20") Integer size, @PathVariable Gender gender) {
		Pageable pageable = PageRequest.of(page, size);
		List<EmployeesDto> emplist = employeeservice.getEmployeeByGender(gender, pageable);
		return new ResponseEntity<List<EmployeesDto>>(emplist, HttpStatus.OK);
	}

	// url = http://localhost:9091/api/v1/adminhrmsconsumer/salaries/{empno}
	// To Update The SalaryByRating By empNo 
	@PutMapping("/salaries/{empno}")
	public ResponseEntity<ResponseDto> updateSalaryByRating(@RequestBody RequestDto dto, @PathVariable Integer empno) {
		ResponseDto response = adminhrmsconsumerservice.updateSalaryByRating(dto, empno);
		return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
	}

}
