
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

import com.hrmanagementsystem.dto.TitlesDto;
import com.hrmanagementsystem.service.TitlesService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/titles")
public class TitlesController {

	@Autowired
	private TitlesService titlesService;

	// url = http://localhost:9091/api/v1/titles
	// To Fetch the List Of All Titles
	@GetMapping()
	public ResponseEntity<List<TitlesDto>> getAllTitles() {
		List<TitlesDto> titlesList = titlesService.getAllTitles();
		return new ResponseEntity<List<TitlesDto>>(titlesList, HttpStatus.OK);
	}

	
	// url = http://localhost:9091/api/v1/titles/empno/{empno}/fromdate/{fromdate}/title/{title}
	// To Search the All Titles By EmpNoAndFromDateAndTitle
	@GetMapping("/empno/{empno}/fromdate/{fromdate}/title/{title}")
	public ResponseEntity<TitlesDto> getTitlesByEmpNoAndFromDateAndTitle(@PathVariable Integer empno,
			@PathVariable LocalDate fromdate, @PathVariable String title) {
		TitlesDto titles = titlesService.getTitlesByEmpNoAndFromDateAndTitle(empno, fromdate, title);
		return new ResponseEntity<TitlesDto>(titles, HttpStatus.OK);
	}
	
	
	// url = http://localhost:9091/api/v1/titles/fromdate/{fromdate}
	// To Fetch the List Of All Titles By FromDate
	@GetMapping("/fromdate/{fromdate}")
	public ResponseEntity<List<TitlesDto>> getTitlesByFromDate(@PathVariable LocalDate fromdate) {
		List<TitlesDto> titlesList = titlesService.getTitlesByFromDate(fromdate);
		return new ResponseEntity<List<TitlesDto>>(titlesList, HttpStatus.OK);
	}
	
	// url = http://localhost:9091/api/v1/titles/title/{title}
	// To Fetch The List Of All Titles By Titles
	@GetMapping("/title/{title}")
	public ResponseEntity<List<TitlesDto>> getTitlesByTitles(@PathVariable String title) {
		List<TitlesDto> titlesList = titlesService.getTitlesByTitles(title);
		return new ResponseEntity<List<TitlesDto>>(titlesList, HttpStatus.OK);
	}
	
	// url = http://localhost:9091/api/v1/titles/title/{title}/fromdate/{fromdate}
	// To Fetch The List Of All Titles BY TitleAndFromDate
	@GetMapping("/title/{title}/fromdate/{fromdate}")
	public ResponseEntity<List<TitlesDto>> getTitlesByTitlesAndFromDate(@PathVariable String title,@PathVariable LocalDate fromdate){
		List<TitlesDto> titlesList = titlesService.getTitlesByTitlesAndFromDate(title,fromdate);
		return new ResponseEntity<List<TitlesDto>>(titlesList, HttpStatus.OK);
	}
	

	// url = http://localhost:9091/api/v1/titles/empno/{empno}/fromdate/{fromdate}
	// To Fetch The List Of All Titles By EmpNoAndFromDate
	@GetMapping("/empno/{empno}/fromdate/{fromdate}")
	public ResponseEntity<List<TitlesDto>> getTitlesByEmpNoAndFromDate(@PathVariable Integer empno,@PathVariable LocalDate fromdate){
		List<TitlesDto> titlesList = titlesService.getTitlesByEmpNoAndFromDate(empno,fromdate);
		return new ResponseEntity<List<TitlesDto>>(titlesList, HttpStatus.OK);
	}
	
	// url = http://localhost:9091/api/v1/titles/empno/{empno}/title/{title}
	// To Fetch The List Of All Titles By EmpNoAndTitle
	@GetMapping("/empno/{empno}/title/{title}")
	public ResponseEntity<List<TitlesDto>> getTitlesByEmpNoAndTitle(@PathVariable Integer empno,@PathVariable String title){
		List<TitlesDto> titlesList = titlesService.getTitlesByEmpNoAndTitle(empno,title);
		return new ResponseEntity<List<TitlesDto>>(titlesList, HttpStatus.OK);
	}
	
	// url = http://localhost:9091/api/v1/titles/romdate/{fromdate}
	// To Update The List Of All Titles By FromDate
	@PutMapping("fromdate/{fromdate}")
	public ResponseEntity<List<TitlesDto>> updateTitleByFromDate(@RequestBody TitlesDto dto, @PathVariable LocalDate fromdate){
		List<TitlesDto> titlesList = titlesService.updateTitleByFromDate(dto,fromdate);
		return new ResponseEntity<List<TitlesDto>>(titlesList, HttpStatus.OK);
	}
	
	// url = http://localhost:9091/api/v1/titles
	// To Add A New Titles
	@PostMapping()
	public ResponseEntity<String> addTitle(@Valid @RequestBody TitlesDto dto) {
		String response = titlesService.addTitle(dto);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	// url = http://localhost:9091/api/v1/titles/title/{title}
	// To Update The List Of All Titles By Title
	@PutMapping("title/{title}")
	public ResponseEntity<List<TitlesDto>> updateTitlesByTitle(@RequestBody TitlesDto dto, @PathVariable String title){
		List<TitlesDto> titlesList = titlesService.updateTitlesByTitle(dto,title);
		return new ResponseEntity<List<TitlesDto>>(titlesList, HttpStatus.OK);
	}
	
	
	// url = http://localhost:9091/api/v1/titles/{empno}
	// To Update The List Of All Titles By EmpNO
	@PutMapping("/{empno}")
	public ResponseEntity<List<TitlesDto>> updateTitlesByEmpNo(@RequestBody TitlesDto dto, @PathVariable Integer empno){
		List<TitlesDto> titlesList = titlesService.updateTitlesByEmpNo(dto,empno);
		return new ResponseEntity<List<TitlesDto>>(titlesList, HttpStatus.OK);
	}
	
	
	// url = http://localhost:9091/api/v1/titles/empno/{empno}/fromdate/{fromdate}/title/{title}
	// To Update The All Titles By EmpNoAndFromDateAndTitle
	@PutMapping("empno/{empno}/fromdate/{fromdate}/title/{title}")
	public ResponseEntity<String> updateTitlesByEmpNoFromDateTitle(@RequestBody TitlesDto dto, @PathVariable Integer empno,
			@PathVariable LocalDate fromdate, @PathVariable String title){
		String response = titlesService.updateTitlesByEmpNoFromDateTitle(dto,empno, fromdate, title);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	
}


	

