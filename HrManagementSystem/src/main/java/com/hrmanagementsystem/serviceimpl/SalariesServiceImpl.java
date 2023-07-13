package com.hrmanagementsystem.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hrmanagementsystem.dto.SalariesDto;
import com.hrmanagementsystem.entity.Employees;
import com.hrmanagementsystem.entity.Salaries;
import com.hrmanagementsystem.exception.UniversalExceptionHandler;
import com.hrmanagementsystem.repository.SalariesRepository;
import com.hrmanagementsystem.service.SalariesService;

@Service
public class SalariesServiceImpl implements SalariesService {

	@Autowired
	private SalariesRepository salariesrepository;

	// Method to retrieve salaries
	@Override
	public List<SalariesDto> getAllSalaries() {
		List<Salaries> salarylist = salariesrepository.findAll();
		List<SalariesDto> salarydtolist = new ArrayList<>();
		for (Salaries salary : salarylist) {
			SalariesDto salarydto = new SalariesDto();
			BeanUtils.copyProperties(salary, salarydto);
			salarydto.setEmpNo(salary.getEmployees().getEmpNo());
			salarydtolist.add(salarydto);
		}
		return salarydtolist;
	}

	// Method to retrieve salaries by fromDate
	@Override
	public List<SalariesDto> getSalariesByFromDate(LocalDate fromdate) {
		List<Salaries> salariesList = salariesrepository.findByFromDate(fromdate);
		List<SalariesDto> salariesDtoList = new ArrayList<>();
		for (Salaries salaries : salariesList) {
			SalariesDto salariesdto = new SalariesDto();
			BeanUtils.copyProperties(salaries, salariesdto);
			salariesdto.setEmpNo(salaries.getEmployees().getEmpNo());
			salariesDtoList.add(salariesdto);
		}
		return salariesDtoList;
	}

	// Method to retrieve salaries by empNo and fromDate
	public SalariesDto getSalariesByEmpNoAndFromDate(Integer empno, LocalDate fromdate) {
		Salaries salaries = salariesrepository.findByEmpNoAndFromDate(empno, fromdate);
		SalariesDto salariesdto = new SalariesDto();
		BeanUtils.copyProperties(salaries, salariesdto);
		salariesdto.setEmpNo(salaries.getEmployees().getEmpNo());
		return salariesdto;
	}

	// Method to retrieve salaries by empNo
	public List<SalariesDto> getSalariesByEmpNo(Integer empno) {
		List<Salaries> salarieslist = salariesrepository.findByEmpNo(empno);
		List<SalariesDto> salariesdtolist = new ArrayList<>();
		for (Salaries sal : salarieslist) {
			SalariesDto dto = new SalariesDto();
			BeanUtils.copyProperties(sal, dto);
			dto.setEmpNo(sal.getEmployees().getEmpNo());
			salariesdtolist.add(dto);
		}
		return salariesdtolist;
	}

	// Method to retrieves salaries between the minimum and maximum amounts
	public List<SalariesDto> getSalariesBetweenMinAndMax(Integer minsalary, Integer maxsalary, Pageable pageable) {
		Page<Salaries> salarieslist = salariesrepository.findSalariesBetweenMaxAndMin(minsalary, maxsalary, pageable);
		List<SalariesDto> salariesdtolist = new ArrayList<>();
		for (Salaries sal : salarieslist) {
			SalariesDto dto = new SalariesDto();
			BeanUtils.copyProperties(sal, dto);
			dto.setEmpNo(sal.getEmployees().getEmpNo());
			salariesdtolist.add(dto);
		}
		return salariesdtolist;

	}

	// Method to updates salaries by from date
	public List<SalariesDto> updateSalariesByFromDate(SalariesDto dto, LocalDate fromdate) {
		List<Salaries> salarieslist = salariesrepository.findByFromDate(fromdate);
		if (!salarieslist.isEmpty()) {
			List<SalariesDto> salariesdtolist = new ArrayList<>();
			for (Salaries salary : salarieslist) {
				salary.setSalary(dto.getSalary());
				SalariesDto salarydto = new SalariesDto();
				BeanUtils.copyProperties(salary, salarydto);
				salarydto.setEmpNo(dto.getEmpNo());
				salariesrepository.save(salary);
				salariesdtolist.add(salarydto);
			}
			return salariesdtolist;
		}
		throw new UniversalExceptionHandler("Validation failed");
	}

	// Method to add salaries
	@Override
	public String addSalary(SalariesDto dto) {

		Salaries salary = new Salaries();
		Employees e = new Employees(dto.getEmpNo());
		BeanUtils.copyProperties(dto, salary);
		salary.setEmployees(e);
		try {
			salariesrepository.save(salary);
			return "New Salary details  added Successfully";
		} catch (Exception ex) {
			throw new UniversalExceptionHandler("Validation failed");
		}
	}

	// Method to updates salaries by empNo
	@Override
	public List<SalariesDto> updateSalariesByEmpNo(SalariesDto dto, Integer empno) {
		List<Salaries> salarieslist = salariesrepository.findByEmpNo(empno);
		if (!salarieslist.isEmpty()) {
			List<SalariesDto> salariesdtolist = new ArrayList<>();
			for (Salaries sal : salarieslist) {

				sal.setSalary(dto.getSalary());
				SalariesDto saldto = new SalariesDto();
				BeanUtils.copyProperties(sal, saldto);
				salariesrepository.save(sal);
				saldto.setEmpNo(dto.getEmpNo());
				salariesdtolist.add(saldto);
			}
			return salariesdtolist;
		}
		throw new UniversalExceptionHandler("Validation failed");
	}

	// Method to updates salaries by empNo and from date.
	public String updateSalariesByEmpNoAndFromDate(SalariesDto dto, Integer empno, LocalDate fromdate) {
		Salaries salary = salariesrepository.findByEmpNoAndFromDate(empno, fromdate);
		salary.setSalary(dto.getSalary());
		SalariesDto salarydto = new SalariesDto();
		BeanUtils.copyProperties(salary, salarydto);
		salariesrepository.save(salary);

		return "salary updated Successfully";
	}

}
