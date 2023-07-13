package com.hrmanagementsystem.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hrmanagementsystem.dto.EmployeesDto;
import com.hrmanagementsystem.entity.Employees;
import com.hrmanagementsystem.entity.Gender;

public interface EmployeeService {
	public EmployeesDto getEmployeeByNo(Integer id);

	public List<EmployeesDto> getEmployeeByFirstName(String firstName);

	public List<EmployeesDto> getEmployeeByLastName(String lastName);

	public EmployeesDto updateEmployeeLastName(EmployeesDto empdto, Integer id);

	public String addEmployee(EmployeesDto empdto);

	public EmployeesDto updateEmployeeFirstName(EmployeesDto empdto, Integer id);

	public List<EmployeesDto> getEmployeeByHireDate(LocalDate hireDate);

	public List<EmployeesDto> getEmployeeByBirthDate(LocalDate birthDate);

	public List<EmployeesDto> getEmployeeByGender(Gender gender,Pageable pageable);

	public Page<Employees> getAllEmployees(Pageable Pageable);

	public EmployeesDto updateEmployeeHireDate(EmployeesDto empdto, Integer id);

	public EmployeesDto updateEmployeeBirthDate(EmployeesDto empdto, Integer id);
}
