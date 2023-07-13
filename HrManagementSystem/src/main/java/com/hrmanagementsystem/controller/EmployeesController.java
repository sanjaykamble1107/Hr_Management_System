package com.hrmanagementsystem.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrmanagementsystem.dto.EmployeesDto;
import com.hrmanagementsystem.entity.Employees;
import com.hrmanagementsystem.entity.Gender;
import com.hrmanagementsystem.service.EmployeeService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/employees")
public class EmployeesController {
	@Autowired
	private EmployeeService employeeService;


	// url = http://localhost:9091/api/v1/employees
	// To Add A New Employee
	@PostMapping
	public ResponseEntity<String> saveEmployee(@Valid @RequestBody EmployeesDto empdto) {
		String response = employeeService.addEmployee(empdto);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	// url = http://localhost:9091/api/v1/employees/getallemployees
	// To Fetch The All Employees
	@GetMapping("/getallemployees")
	public ResponseEntity<Page<Employees>> getAllEmployees(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "20") Integer size) {
		Pageable pageable=PageRequest.of(page, size);
		Page<Employees> employeesList = employeeService.getAllEmployees(pageable);
		return new ResponseEntity<Page<Employees>>(employeesList, HttpStatus.OK);
	}
	

	// url = http://localhost:9091/api/v1/employees/{empno}
	// To Fetch The All Employees By Id
	@GetMapping("/{empno}")
	public ResponseEntity<EmployeesDto> getEmployeeById(@PathVariable Integer empno) {
		EmployeesDto employeeByNo = employeeService.getEmployeeByNo(empno);
		System.out.println(employeeByNo);
		return new ResponseEntity<EmployeesDto>(employeeByNo, HttpStatus.OK);
	}


	// url = http://localhost:9091/api/v1/employees/firstname/{fn}
	// To Search The List Of Employees By FirstName
	@GetMapping("/firstname/{fn}")
	public ResponseEntity<List<EmployeesDto>> getEmployeeByFirstName(@PathVariable String fn) {
		List<EmployeesDto> employeeByFirstName = employeeService.getEmployeeByFirstName(fn);
		return new ResponseEntity<List<EmployeesDto>>(employeeByFirstName, HttpStatus.OK);
	}

	// url = http://localhost:9091/api/v1/employees/lastname/{ln}
	// To Search The List Of Employees By LastName
	@GetMapping("/lastname/{ln}")
	public ResponseEntity<List<EmployeesDto>> getEmployeeByLastName(@PathVariable String ln) {
		List<EmployeesDto> employeeByLastName = employeeService.getEmployeeByLastName(ln);
		return new ResponseEntity<List<EmployeesDto>>(employeeByLastName, HttpStatus.OK);
	}

	// url = http://localhost:9091/api/v1/employees/lastname/{empno}
	// To Update The Employees Of LastName By EmpNo
	@PutMapping("/lastname/{empno}")
	public ResponseEntity<EmployeesDto> updateEmployeeLastName(@RequestBody EmployeesDto empdto,
			@PathVariable Integer empno) {
		EmployeesDto employee = employeeService.updateEmployeeLastName(empdto, empno);
		return new ResponseEntity<EmployeesDto>(employee, HttpStatus.OK);
	}
	
	// url = http://localhost:9091/api/v1/employees/firstname/{empno}
	// To Update The Employees Of FirstName By EmpNO
	@PutMapping("/firstname/{empno}")
	public ResponseEntity<EmployeesDto> updateEmployeefirstName(@RequestBody EmployeesDto empdto,
			@PathVariable Integer empno) {
		EmployeesDto employee = employeeService.updateEmployeeFirstName(empdto, empno);
		return new ResponseEntity<EmployeesDto>(employee, HttpStatus.OK);
	}
	
	// url = http://localhost:9091/api/v1/employees/hiredate/{hiredate}
	// To Search The List Of Employees By HireDate
	@GetMapping("/hiredate/{hiredate}")
	public ResponseEntity<List<EmployeesDto>> getEmployeeByHireDate(@PathVariable LocalDate hiredate) {
		List<EmployeesDto> employeeByHireDate = employeeService.getEmployeeByHireDate(hiredate);
		return new ResponseEntity<List<EmployeesDto>>(employeeByHireDate, HttpStatus.OK);
	}


	// url = http://localhost:9091/api/v1/employees/birthdate/{birthdate}
	// To Search The List Of Employees By BirthDate
	@GetMapping("/birthdate/{birthdate}")
	public ResponseEntity<List<EmployeesDto>> getEmployeeByBirthDate(@PathVariable LocalDate birthdate) {
		List<EmployeesDto> employeeByBirthDate = employeeService.getEmployeeByBirthDate(birthdate);
		return new ResponseEntity<List<EmployeesDto>>(employeeByBirthDate, HttpStatus.OK);
	}

	// url = http://localhost:9091/api/v1/employees/gender/{gender}
	// To Search The List Of Employees By Gender	
	@GetMapping("/gender/{gender}")
	public ResponseEntity<List<EmployeesDto>> getEmployeeByGender(@PathVariable Gender gender) {
		Pageable pageable=PageRequest.of(0, 20);
		List<EmployeesDto> employeeByGender = employeeService.getEmployeeByGender(gender,pageable);
		return new ResponseEntity<List<EmployeesDto>>(employeeByGender, HttpStatus.OK);
	}

	// url = http://localhost:9091/api/v1/employees/hiredate/{empno}
	// To Update The Employees Of HireDate By EmpNo
	@PutMapping("/hiredate/{empno}")
	public ResponseEntity<EmployeesDto> updateEmployeeHireDate(@RequestBody EmployeesDto empdto,
			@PathVariable Integer empno) {
//		System.out.println("THE NEW HIREDATE IS -------------- " + empdto.getHireDate());
		EmployeesDto employee = employeeService.updateEmployeeHireDate(empdto, empno);
		return new ResponseEntity<EmployeesDto>(employee, HttpStatus.OK);
	}

	// url = http://localhost:9091/api/v1/employees/birthdate/{empno}
	// To Update The Employees Of BirthDate By EmpNO
	@PutMapping("/birthdate/{empno}")
	public ResponseEntity<EmployeesDto> updateEmployeeBirthDate(@RequestBody EmployeesDto empdto,
			@PathVariable Integer empno) {
		EmployeesDto employee = employeeService.updateEmployeeBirthDate(empdto, empno);
		return new ResponseEntity<EmployeesDto>(employee, HttpStatus.OK);
	}

}
