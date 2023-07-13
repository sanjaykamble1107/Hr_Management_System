package com.hrmanagementsystem.controller;

import java.time.LocalDate;
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

import com.hrmanagementsystem.dto.DepartmentEmployeeDto;
import com.hrmanagementsystem.serviceimpl.DepartmentEmployeeServiceImpl;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/deptemp")
public class DepartmentEmployeeController {

	@Autowired
	private DepartmentEmployeeServiceImpl departmentEmployeeService;
	
	// url = http://localhost:9091/api/v1/deptemp/adddeptemp
	// To Add A new DepartmentEmployee
	@PostMapping("/adddeptemp")
	public ResponseEntity<String> saveDepartment(@Valid @RequestBody DepartmentEmployeeDto dto) {
		String response = departmentEmployeeService.addDepartmentEmployee(dto);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	// url = http://localhost:9091/api/v1/deptemp
	// To Fetch The List Of All DepartmentEmployee
	@GetMapping()
	public ResponseEntity<List<DepartmentEmployeeDto>> getAllDepartmentEmployee() {
		List<DepartmentEmployeeDto> deptempList = departmentEmployeeService.getAllDepartmentEmployee();
		return new ResponseEntity<List<DepartmentEmployeeDto>>(deptempList, HttpStatus.OK);
	}
	
	// url = http://localhost:9091/api/v1/deptemp/deptno/{deptno}/fromdate/{fromdate}
	// To Fetch The List Of DepartmentEmployee By DeptNo And FromDate
	@GetMapping("/deptno/{deptno}/fromdate/{fromdate}")
	public ResponseEntity<List<DepartmentEmployeeDto>> getDepartmentEmployeeByDeptNoAndFromDate(@PathVariable String deptno, @PathVariable LocalDate fromdate) {
		List<DepartmentEmployeeDto> deptempList = departmentEmployeeService.getDepartmentEmployeeByDeptNoAndFromDate(deptno,fromdate);
		return new ResponseEntity<List<DepartmentEmployeeDto>>(deptempList, HttpStatus.OK);
	}
	
	// url = http://localhost:9091/api/v1/deptemp/empno/{empno}/fromdate/{fromdate}
	// To Fetch The DepartmentEmployee By EmpNo And FromDate
	@GetMapping("/empno/{empno}/fromdate/{fromdate}")
	public ResponseEntity <DepartmentEmployeeDto> getDepartmentEmployeeByEmpNoAndFromDate(@PathVariable Integer empno, @PathVariable LocalDate fromdate) {
		DepartmentEmployeeDto deptemp = departmentEmployeeService.getDepartmentEmployeeByEmpNoAndFromDate(empno,fromdate);
		return new ResponseEntity<DepartmentEmployeeDto>(deptemp, HttpStatus.OK);
	}
	
	// url = http://localhost:9091/api/v1/deptemp/empno/{empno}/deptno/{deptno}
	// To Search The DepartmentEmployee By EmpNo And DeptNo
	@GetMapping("/empno/{empno}/deptno/{deptno}")
	public ResponseEntity<DepartmentEmployeeDto> getDepartmentEmployeeByEmpNoAndDeptNo(@PathVariable Integer empno, @PathVariable String deptno) {
		    DepartmentEmployeeDto deptempdto = departmentEmployeeService.getDepartmentEmployeeByEmpNoAndDeptNo(empno,deptno);
		return new ResponseEntity<DepartmentEmployeeDto>(deptempdto, HttpStatus.OK);
	}
	

	// url = http://localhost:9091/api/v1/deptemp/empno/{empno}/deptno/{deptno}/fromdate/{fromdate}
	// To Fetch The List Of DepartmentEmployee By EmpNo And FromDate And DeptNo
	@GetMapping("/empno/{empno}/deptno/{deptno}/fromdate/{fromdate}")
	public ResponseEntity<List<DepartmentEmployeeDto>> getDepartmentEmployeeByEmpNoAndDeptNoAndFromDate(@PathVariable Integer empno, @PathVariable String deptno, @PathVariable LocalDate fromdate) {
		List<DepartmentEmployeeDto> deptempdtolist = departmentEmployeeService.getDepartmentEmployeeByEmpNoAndDeptNoAndFromDate(empno,deptno,fromdate);
		return new ResponseEntity<List<DepartmentEmployeeDto>>(deptempdtolist, HttpStatus.OK);
	}
	
	// url = http://localhost:9091/api/v1/deptemp/{empno}/{deptno}
	// To Update The DepartmentEmployee By EmpNo And DeptNo
	@PutMapping("/{empno}/{deptno}")
	public ResponseEntity<String> updateDepartmentEmployeeByEmpNoAndDeptNo(@RequestBody DepartmentEmployeeDto dto ,@PathVariable Integer empno, @PathVariable String deptno) {
		String deptemp = departmentEmployeeService.updateDepartmentEmployeeByEmpNoAndDeptNo(dto,empno,deptno);
		return new ResponseEntity<String>(deptemp, HttpStatus.OK);
	}
	
	// url = http://localhost:9091/api/v1/deptemp/empno/{empno}/fromdate/{fromdate}
	// To Update The DepartmentEmployee By EmpNo And FromDate
	@PutMapping("/empno/{empno}/fromdate/{fromdate}")
	public ResponseEntity<String> updateDepartmentEmployeeByEmpNoAndFromDate(@RequestBody DepartmentEmployeeDto dto ,@PathVariable Integer empno, @PathVariable LocalDate fromdate) {
		String response = departmentEmployeeService.updateDepartmentEmployeeByEmpNoAndFromDate(dto,empno,fromdate);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	

	// url = http://localhost:9091/api/v1/deptemp/deptno/{deptno}/fromdate/{fromdate}
	// To Update The List Of DepartmentEmployee By DeptNo And FromDate
	@PutMapping("/deptno/{deptno}/fromdate/{fromdate}")
	public ResponseEntity<List<DepartmentEmployeeDto>> updateDepartmentEmployeeByDeptNoAndFromDate(@RequestBody DepartmentEmployeeDto dto ,@PathVariable String deptno, @PathVariable LocalDate fromdate) {
		List<DepartmentEmployeeDto>	 deptemplist = departmentEmployeeService.updateDepartmentEmployeeByDeptNoAndFromDate(dto,deptno,fromdate);
		return new ResponseEntity<List<DepartmentEmployeeDto>>(deptemplist, HttpStatus.OK); 

	}
	
	// url = http://localhost:9091/api/v1/deptemp/empno/{empno}/deptno/{deptno}/fromdate/{fromdate}
	// To Update The DepartmentEmployee By DeptNo And FromDate And EmpNo
	@PutMapping("/empno/{empno}/deptno/{deptno}/fromdate/{fromdate}")
	public ResponseEntity<String> updateDepartmentEmployeeByEmpNoAndDeptNoAndFromDate(@RequestBody DepartmentEmployeeDto dto ,@PathVariable Integer empno,@PathVariable String deptno, @PathVariable LocalDate fromdate) {
		String	response = departmentEmployeeService.updateDepartmentEmployeeByEmpNoAndDeptNoAndFromDate(dto,empno,deptno,fromdate);
		return new ResponseEntity<String>(response, HttpStatus.OK); 
  }
}
