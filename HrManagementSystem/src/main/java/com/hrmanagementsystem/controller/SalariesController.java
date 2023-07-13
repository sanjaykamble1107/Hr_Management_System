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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrmanagementsystem.dto.SalariesDto;
import com.hrmanagementsystem.service.SalariesService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/salaries")
public class SalariesController {

	@Autowired
	private SalariesService salariesService;


	// url = http://localhost:9091/api/v1/salaries/addsalary
	// To Add A New Salary
	@PostMapping
	public ResponseEntity<String> addSalary(@Valid @RequestBody SalariesDto dto) {
		String response = salariesService.addSalary(dto);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}


	// url = http://localhost:9091/api/v1/salaries
	// To Fetch The List Of All Salaries
	@GetMapping()
	public ResponseEntity<List<SalariesDto>> getAllSalaries() {
		List<SalariesDto> salariesList = salariesService.getAllSalaries();
		return new ResponseEntity<List<SalariesDto>>(salariesList, HttpStatus.OK);
	}

	// url = http://localhost:9091/api/v1/salaries/fromdate/{fromdate}
	// To Fetch The List Of All Salaries By FromDate
	@GetMapping("/fromdate/{fromdate}")
	public ResponseEntity<List<SalariesDto>> getSalariesByFromDate(@PathVariable LocalDate fromdate) {
		List<SalariesDto> salariesList = salariesService.getSalariesByFromDate(fromdate);
		return new ResponseEntity<List<SalariesDto>>(salariesList, HttpStatus.OK);
	}

	// url = http://localhost:9091/api/v1/salaries/empno/{empno}/fromdate/{fromdate}
	// To Search The All Salaries By EmpNoAndFromDate
	@GetMapping("/empno/{empno}/fromdate/{fromdate}")
	public ResponseEntity<SalariesDto> getSalariesByEmpNoAndFromDate(@PathVariable Integer empno,
			@PathVariable LocalDate fromdate) {
		SalariesDto salaries = salariesService.getSalariesByEmpNoAndFromDate(empno, fromdate);
		return new ResponseEntity<SalariesDto>(salaries, HttpStatus.OK);
	}

	
	// url = http://localhost:9091/api/v1/salaries/empno/{empno}
	// To Fetch The List Of All Salaries By EmpNO
	@GetMapping("/empno/{empno}")
	public ResponseEntity<List<SalariesDto>> getSalariesByEmpNo(@PathVariable Integer empno) {
		List<SalariesDto> salarieslist = salariesService.getSalariesByEmpNo(empno);
		return new ResponseEntity<List<SalariesDto>>(salarieslist, HttpStatus.OK);
	}
	
	// url=http://localhost:9091/api/v1/salaries/salary/{minsalary}/{maxsalary}
	// To Fetch The List Of All Salaries Between MinAndMax
	@GetMapping("salary/{minsalary}/{maxsalary}")
	public ResponseEntity<List<SalariesDto>> getSalariesBetweenMinAndMax(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "20") Integer size, @PathVariable Integer minsalary,
			@PathVariable Integer maxsalary) {
		Pageable pageable=PageRequest.of(page, size);
		List<SalariesDto> salarieslist = salariesService.getSalariesBetweenMinAndMax(minsalary, maxsalary,pageable);
		return new ResponseEntity<List<SalariesDto>>(salarieslist, HttpStatus.OK);
	}

	// url = http://localhost:9091/api/v1/salaries/salary/fromdate/{fromdate}
	// To Update The List Of All Salaries By FromDate
	@PutMapping("salary/fromdate/{fromdate}")
	public ResponseEntity<List<SalariesDto>> updateSalariesByFromDate(@RequestBody SalariesDto dto,
			@PathVariable LocalDate fromdate) {
		List<SalariesDto> salarieslist = salariesService.updateSalariesByFromDate(dto, fromdate);
		return new ResponseEntity<List<SalariesDto>>(salarieslist, HttpStatus.OK);
	}
	
	// url = http://localhost:9091/api/v1/salaries/empno/{empno}
	// To Update The List Of All Salaries By EmpNo	
	@PutMapping("empno/{empno}")
	public ResponseEntity<List<SalariesDto>> updateSalariesByEmpNo(@RequestBody SalariesDto dto,
			@PathVariable Integer empno) {
		List<SalariesDto> salarieslist = salariesService.updateSalariesByEmpNo(dto, empno);
		return new ResponseEntity<List<SalariesDto>>(salarieslist, HttpStatus.OK);
	}
	

	// url = http://localhost:9091/api/v1/salaries/empno/{empno}/fromdate/{fromdate}
	// To Update The All Salaries By EmpNoAndFromDate
	@PutMapping("empno/{empno}/fromdate/{fromdate}")
	public ResponseEntity<String> updateSalariesByEmpNoAndFromDate(@RequestBody SalariesDto dto,
			@PathVariable Integer empno, @PathVariable LocalDate fromdate) {
		String response = salariesService.updateSalariesByEmpNoAndFromDate(dto, empno, fromdate);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
}
