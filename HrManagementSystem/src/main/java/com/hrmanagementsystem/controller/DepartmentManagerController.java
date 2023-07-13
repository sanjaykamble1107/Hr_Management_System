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

import com.hrmanagementsystem.dto.DepartmentManagerDto;
import com.hrmanagementsystem.service.DepartmentManagerService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/deptmanager")
public class DepartmentManagerController {

	@Autowired
	private DepartmentManagerService departmentManagerService;
	
	// url = http://localhost:9091/api/v1/deptmanager/adddeptmanager
	// To Add A new DepartmentManager
	@PostMapping("/adddeptmanager")
	public ResponseEntity<String> saveDepartment(@Valid @RequestBody DepartmentManagerDto dto) {
		String response = departmentManagerService.addDepartmentManager(dto);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	// url = http://localhost:9091/api/v1/deptmanager
	// To Fetch The List Of All DepartmentManager
	@GetMapping()
	public ResponseEntity<List<DepartmentManagerDto>> getAllDepartmentManager() {
		List<DepartmentManagerDto> deptmanagerList = departmentManagerService.getAllDepartmentManager();
		return new ResponseEntity<List<DepartmentManagerDto>>(deptmanagerList, HttpStatus.OK);
	}
	
	// url = http://localhost:9091/api/v1/deptmanager/empno/{empno}/deptno/{deptno}
	// To Search The DepartmentManager By EmpNo And DeptNo
	@GetMapping("/empno/{empno}/deptno/{deptno}")
	public ResponseEntity<DepartmentManagerDto> getDepartmentManagerByEmpNoAndDeptNo(@PathVariable Integer empno, @PathVariable String deptno) {
		DepartmentManagerDto deptmanagerDto = departmentManagerService.getDepartmentManagerByEmpNoAndDeptNo(empno,deptno);
		return new ResponseEntity<DepartmentManagerDto>(deptmanagerDto, HttpStatus.OK);
	}

	// url = http://localhost:9091/api/v1/deptmanager/deptno/{deptno}/fromdate/{fromdate}
	// To Fetch The List Of All DepartmentManager By DeptNo And FromDate
	@GetMapping("/deptno/{deptno}/fromdate/{fromdate}")
	public ResponseEntity<List<DepartmentManagerDto>> getDepartmentManagerByDeptNoAndFromDate(@PathVariable String deptno, @PathVariable LocalDate fromdate) {
		List<DepartmentManagerDto> deptmanagerDtoList = departmentManagerService.getDepartmentManagerByDeptNoAndFromDate(deptno,fromdate);
		return new ResponseEntity<List<DepartmentManagerDto>>(deptmanagerDtoList, HttpStatus.OK);
	}
	
	// url = http://localhost:9091/api/v1/deptmanager/empno/{empno}/fromdate/{fromdate}
	// To Get The List Of All DepartmentManager By EmpNo And FromDate
	@GetMapping("/empno/{empno}/fromdate/{fromdate}")
	public ResponseEntity<List<DepartmentManagerDto>> getDepartmentManagerByEmpNoAndFromDate(@PathVariable Integer empno, @PathVariable LocalDate fromdate) {
		List<DepartmentManagerDto> deptmanagerDtoList = departmentManagerService.getDepartmentManagerByEmpNoAndFromDate(empno,fromdate);
		return new ResponseEntity<List<DepartmentManagerDto>>(deptmanagerDtoList, HttpStatus.OK);
	}
	
	// url = http://localhost:9091/api/v1/deptmanager/empno/{empno}/deptno/{deptno}/fromdate/{fromdate}
	// To Get The List Of All DepartmentManager By EmpNo And DeptNo And FromDate
	@GetMapping("/empno/{empno}/deptno/{deptno}/fromdate/{fromdate}")
	public ResponseEntity<List<DepartmentManagerDto>> getDepartmentManagerByEmpNoAndDeptNoAndFromDate(@PathVariable Integer empno,@PathVariable String deptno, @PathVariable LocalDate fromdate) {
		List<DepartmentManagerDto> deptmanagerDtoList = departmentManagerService.getDepartmentManagerByEmpNoAndDeptNoAndFromDate(empno,deptno,fromdate);
		return new ResponseEntity<List<DepartmentManagerDto>>(deptmanagerDtoList, HttpStatus.OK);
	}
	
	// url = http://localhost:9091/api/v1/deptmanager/{empno}/{deptno}
	// To Update The DepartmentManager By EmpNo And DeptNo
	@PutMapping("/{empno}/{deptno}")
	public ResponseEntity<String> updateDepartmentEmployeeByEmpNoAndDeptNo(@RequestBody DepartmentManagerDto dto ,@PathVariable Integer empno, @PathVariable String deptno) {
		String deptmanager = departmentManagerService.updateDepartmentManagerByEmpNoAndDeptNo(dto,empno,deptno);
		return new ResponseEntity<String>(deptmanager, HttpStatus.OK);
	}
	
	// url = http://localhost:9091/api/v1/deptmanager/deptno/{deptno}/fromdate/{fromdate}
	// To Update The DepartmentManager By DeptNo And FromDate
	@PutMapping("/deptno/{deptno}/fromdate/{fromdate}")
	public ResponseEntity<List<DepartmentManagerDto>> updateDepartmentManagerByDeptNoAndFromDate(@RequestBody DepartmentManagerDto dto ,@PathVariable String deptno, @PathVariable LocalDate fromdate) {
		List<DepartmentManagerDto>	 deptemplist = departmentManagerService.updateDepartmentManagerByDeptNoAndFromDate(dto,deptno,fromdate);
		return new ResponseEntity<List<DepartmentManagerDto>>(deptemplist, HttpStatus.OK); 

	}
	
	// url = http://localhost:9091/api/v1/deptmanager/empno/{empno}/deptno/{deptno}/fromdate/{fromdate}
	// To Update The DepartmentManager By EmpNo And DeptNo And FromDate  
	@PutMapping("/empno/{empno}/deptno/{deptno}/fromdate/{fromdate}")
	public ResponseEntity<String> updateDepartmentEmployeeByEmpNoAndDeptNoAndFromDate(@RequestBody DepartmentManagerDto dto ,@PathVariable Integer empno,@PathVariable String deptno, @PathVariable LocalDate fromdate) {
		String	response = departmentManagerService.updateDepartmentManagerByEmpNoAndDeptNoAndFromDate(dto,empno,deptno,fromdate);
		return new ResponseEntity<String>(response, HttpStatus.OK); 
	}

	// url = http://localhost:9091/api/v1/deptmanager/empno/{empno}/fromdate/{fromdate}
	// To Update The DepartmentManager By EmpNo And FromDate
	@PutMapping("/empno/{empno}/fromdate/{fromdate}")
	public ResponseEntity<String> updateDepartmentEmployeeByEmpNoAndFromDate(@RequestBody DepartmentManagerDto dto ,@PathVariable Integer empno, @PathVariable LocalDate fromdate) {
		String deptmanager = departmentManagerService.updateDepartmentManagerByEmpNoAndFromDate(dto,empno,fromdate);
		return new ResponseEntity<String>(deptmanager, HttpStatus.OK);
	}
	
}
