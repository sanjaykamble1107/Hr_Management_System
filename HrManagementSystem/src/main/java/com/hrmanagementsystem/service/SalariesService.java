package com.hrmanagementsystem.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.hrmanagementsystem.dto.SalariesDto;

public interface SalariesService {

	public List<SalariesDto> getAllSalaries();
	public List<SalariesDto> getSalariesByFromDate(LocalDate fromdate);
	public SalariesDto getSalariesByEmpNoAndFromDate(Integer empno, LocalDate fromdate);
	public List<SalariesDto> getSalariesByEmpNo(Integer empno);
	public List<SalariesDto> getSalariesBetweenMinAndMax(Integer minsalary, Integer maxsalary,Pageable pageable);
	public List<SalariesDto> updateSalariesByFromDate(SalariesDto dto, LocalDate fromdate);
	public String addSalary(SalariesDto dto);
	public List<SalariesDto> updateSalariesByEmpNo(SalariesDto dto, Integer empno);
	public String updateSalariesByEmpNoAndFromDate(SalariesDto dto,  Integer empno, LocalDate fromdate);
}

