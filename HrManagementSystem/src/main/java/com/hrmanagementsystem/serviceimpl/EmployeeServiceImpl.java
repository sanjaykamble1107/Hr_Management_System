package com.hrmanagementsystem.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hrmanagementsystem.dto.EmployeesDto;
import com.hrmanagementsystem.entity.Employees;
import com.hrmanagementsystem.entity.Gender;
import com.hrmanagementsystem.exception.UniversalExceptionHandler;
import com.hrmanagementsystem.repository.EmployeeRepository;
import com.hrmanagementsystem.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeerepository;

	// Method to retrieve an employees by empno(id)
	@Override
	public EmployeesDto getEmployeeByNo(Integer id) {
		Optional<Employees> emp = employeerepository.findById(id);
		System.out.println(emp.get());
		EmployeesDto dto = new EmployeesDto();
		BeanUtils.copyProperties(emp.get(), dto);
		return dto;

	}

	// Method to retrieve an employees by First Name
	@Override
	public List<EmployeesDto> getEmployeeByFirstName(String firstName) {
		List<Employees> emp = employeerepository.findByFirstName(firstName);
		List<EmployeesDto> empdtolist = new ArrayList<>();
		for (Employees employee : emp) {
			EmployeesDto empdto = new EmployeesDto();
			BeanUtils.copyProperties(employee, empdto);
			empdtolist.add(empdto);
		}

		return empdtolist;

	}

	// Method to add new employee object in DB
	@Override
	public String addEmployee(EmployeesDto empdto) {
		Employees employee = new Employees();
		BeanUtils.copyProperties(empdto, employee);
		try {
		employeerepository.save(employee);
		return "New Employee Added Succesfully";
		}
		catch(Exception ex) {
			throw new UniversalExceptionHandler("Validation failed");
		}
	}

	// Method to retrieve an employees by Last Name
	@Override
	public List<EmployeesDto> getEmployeeByLastName(String lastName) {

		List<Employees> emp = employeerepository.findBylastName(lastName);
		List<EmployeesDto> empdtolist = new ArrayList<>();
		for (Employees employee : emp) {
			EmployeesDto empdto = new EmployeesDto();

			BeanUtils.copyProperties(employee, empdto);
			empdtolist.add(empdto);
		}

		return empdtolist;
	}

	// Method to update Last Name of an employee by given empno(id)
	@Override
	public EmployeesDto updateEmployeeLastName(EmployeesDto empdto, Integer id) {
		Optional<Employees> employee = employeerepository.findById(id);
		if (!employee.isEmpty()) {
			employee.get().setLastName(empdto.getLastName());
			employeerepository.save(employee.get());
			EmployeesDto employeedto = new EmployeesDto();
			BeanUtils.copyProperties(employee.get(), employeedto);

			return employeedto;
		}
		throw new UniversalExceptionHandler("Validation Failed");
	}

	// Method to update First Name of an employee by given empno(id)
	@Override
	public EmployeesDto updateEmployeeFirstName(EmployeesDto empdto, Integer id) {
		Optional<Employees> employee = employeerepository.findById(id);
		if (!employee.isEmpty()) {
			employee.get().setFirstName(empdto.getFirstName());
			EmployeesDto employeedto = new EmployeesDto();
			BeanUtils.copyProperties(employee.get(), employeedto);
			employeerepository.save(employee.get());
			return employeedto;
		}
		throw new UniversalExceptionHandler("Validation Failed");
	}

	// Method to retrieve an employees by hire date
	@Override
	public List<EmployeesDto> getEmployeeByHireDate(LocalDate hireDate) {
		List<Employees> emplist = employeerepository.findByHireDate(hireDate);
		List<EmployeesDto> empdtolist = new ArrayList<>();
		for (Employees emp : emplist) {
			EmployeesDto empdto = new EmployeesDto();
			BeanUtils.copyProperties(emp, empdto);
			empdtolist.add(empdto);
		}

		return empdtolist;
	}

	// Method to retrieve an employees by birth date
	@Override
	public List<EmployeesDto> getEmployeeByBirthDate(LocalDate birthDate) {
		List<Employees> emplist = employeerepository.findByBirthDate(birthDate);
		List<EmployeesDto> empdtolist = new ArrayList<>();
		for (Employees emp : emplist) {
			EmployeesDto empdto = new EmployeesDto();
			BeanUtils.copyProperties(emp, empdto);
			empdtolist.add(empdto);
		}

		return empdtolist;
	}

	// Method to retrieve an employees by gender
	@Override
	public List<EmployeesDto> getEmployeeByGender(Gender gender, Pageable pageable) {
		List<Employees> emplist = employeerepository.findByGender(gender, pageable);
		List<EmployeesDto> empdtolist = new ArrayList<>();
		for (Employees emp : emplist) {
			EmployeesDto empdto = new EmployeesDto();
			BeanUtils.copyProperties(emp, empdto);

			empdtolist.add(empdto);
		}

		return empdtolist;

	}

	// Retrieve all employees from the repository using pagination
	@Override
	public Page<Employees> getAllEmployees(Pageable pageable) {
		return employeerepository.findAll(pageable);

	}

	// Method to update HireDate for given empno(id)
	@Override
	public EmployeesDto updateEmployeeHireDate(EmployeesDto empdto, Integer id) {

		EmployeesDto employeedto = getEmployeeByNo(id);
		employeedto.setHireDate(empdto.getHireDate());
		Employees employee = new Employees();
		BeanUtils.copyProperties(employeedto, employee);

		employeerepository.save(employee);
		return employeedto;
	}

	// Method to update BirthDate for given empno(id)
	@Override
	public EmployeesDto updateEmployeeBirthDate(EmployeesDto empdto, Integer id) {
		EmployeesDto employeedto = getEmployeeByNo(id);
		employeedto.setBirthDate(empdto.getBirthDate());
		Employees employee = new Employees();
		BeanUtils.copyProperties(employeedto, employee);

		employeerepository.save(employee);
		return employeedto;
	}
}
