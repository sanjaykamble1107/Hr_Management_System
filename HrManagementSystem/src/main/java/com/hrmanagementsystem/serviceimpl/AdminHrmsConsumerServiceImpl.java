package com.hrmanagementsystem.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrmanagementsystem.dto.DepartmentEmployeeDto;
import com.hrmanagementsystem.dto.DepartmentManagerDto;
import com.hrmanagementsystem.dto.DepartmentsDto;
import com.hrmanagementsystem.dto.EmployeesDto;
import com.hrmanagementsystem.dto.GetEmployeeJoinedAfterHireDate;
import com.hrmanagementsystem.dto.RequestDto;
import com.hrmanagementsystem.dto.ResponseDto;
import com.hrmanagementsystem.dto.TitlesDto;
import com.hrmanagementsystem.entity.DepartmentEmployee;
import com.hrmanagementsystem.entity.DepartmentManager;
import com.hrmanagementsystem.entity.Departments;
import com.hrmanagementsystem.entity.Employees;
import com.hrmanagementsystem.entity.Salaries;
import com.hrmanagementsystem.entity.Titles;
import com.hrmanagementsystem.exception.UniversalExceptionHandler;
import com.hrmanagementsystem.repository.DepartmentEmployeeRepository;
import com.hrmanagementsystem.repository.DepartmentManagerRepository;
import com.hrmanagementsystem.repository.DepartmentsRepository;
import com.hrmanagementsystem.repository.EmployeeRepository;
import com.hrmanagementsystem.repository.SalariesRepository;
import com.hrmanagementsystem.repository.TitlesRepository;
import com.hrmanagementsystem.service.AdminHrmsConsumerService;

@Service
public class AdminHrmsConsumerServiceImpl implements AdminHrmsConsumerService {

	// Repositories for various entities
	private final EmployeeRepository employeerepositoryforhrms;
	private final DepartmentEmployeeRepository departmentemployeerepository;
	private final DepartmentManagerRepository departmentmanagerrepository;
	private final TitlesRepository titlesrepository;
	private final DepartmentsRepository departmentrepository;
	private final EmployeeRepository employeerepository;
	private final SalariesRepository salaryrepository;

	// Constructor injection of repositories
	@Autowired
	public AdminHrmsConsumerServiceImpl(EmployeeRepository employeerepositoryforhrms,
			DepartmentEmployeeRepository departmentemployeerepository,
			DepartmentManagerRepository departmentmanagerrepository, TitlesRepository titlesrepository,
			DepartmentsRepository departmentrepository, EmployeeRepository employeerepository,
			SalariesRepository salaryrepository) {

		this.employeerepositoryforhrms = employeerepositoryforhrms;
		this.departmentemployeerepository = departmentemployeerepository;
		this.departmentmanagerrepository = departmentmanagerrepository;
		this.titlesrepository = titlesrepository;
		this.departmentrepository = departmentrepository;
		this.employeerepository = employeerepository;
		this.salaryrepository = salaryrepository;
	}

	// Retrieves a list of employees who joined the company after a specified number
	// of years.
	@Override
	public List<GetEmployeeJoinedAfterHireDate> getEmployeeByLastNYear(Integer noofyear) {
		LocalDate hiredate = LocalDate.now().minusYears(noofyear);
		return employeerepositoryforhrms.findEmployeeByLastNYear(hiredate);

	}

	// Retrieves the count of employees who joined the company in the last N years.
	@Override
	public Integer getCountEmployeeByLastNYear(Integer noofyear) {
		LocalDate hiredate = LocalDate.now().minusYears(noofyear);
		Integer count = employeerepositoryforhrms.findCountEmployeeByNYear(hiredate);
		return count;
	}

	// Retrieves a list of employees who joined the company in a specified year.
	@Override
	public List<EmployeesDto> getEmployeeListByNJoinYear(Integer year) {
		List<Employees> employeeslist = employeerepositoryforhrms.findEmployeeJoinedByNYear(year);
		List<EmployeesDto> employeesDtoList = new ArrayList<>();
		for (Employees emp : employeeslist) {
			EmployeesDto empdto = new EmployeesDto();
			BeanUtils.copyProperties(emp, empdto);
			employeesDtoList.add(empdto);
		}
		return employeesDtoList;
	}

	// Retrieves the count of employees who joined the company in a specific year.
	@Override
	public Integer getEmployeeCountByNJoinYear(Integer year) {
		Integer count = employeerepositoryforhrms.findCountEmployeeJoinedByNYear(year);
		return count;
	}

	// Saves a new department employee.
	@Override
	public ResponseDto saveDeptEmp(DepartmentEmployeeDto dto) {
		DepartmentEmployee deptemp = new DepartmentEmployee();
		BeanUtils.copyProperties(dto, deptemp);
		Employees e = new Employees(dto.getEmpNo());
		deptemp.setEmployees(e);
		Departments d = new Departments(dto.getDeptNo());
		deptemp.setDepartments(d);
		departmentemployeerepository.save(deptemp);
		ResponseDto response = new ResponseDto();
		response.setResponse("Employee is assigned to the department successfully");
		return response;
	}

	// Saves a new department manager
	@Override
	public ResponseDto saveDeptMgr(DepartmentManagerDto dto) {
		DepartmentManager deptmgr = new DepartmentManager();
		BeanUtils.copyProperties(dto, deptmgr);
		Employees e = new Employees(dto.getEmpNo());
		deptmgr.setEmployees(e);
		Departments d = new Departments(dto.getDeptNo());
		deptmgr.setDepartments(d);
		departmentmanagerrepository.save(deptmgr);
		ResponseDto response = new ResponseDto();
		response.setResponse("Employee is assigned  as manager for department successfully");
		return response;
	}

	// Assigns a new title to an employee.
	@Override
	public ResponseDto assignTitle(TitlesDto dto) {
		Titles title = new Titles();
		Employees e = new Employees(dto.getEmpNo());
		BeanUtils.copyProperties(dto, title);
		title.setEmployees(e);
		titlesrepository.save(title);
		ResponseDto response = new ResponseDto();
		response.setResponse("Employee is assigned with title from " + dto.getFromDate() + " to " + dto.getToDate());
		return response;

	}

	// Adds a new department.
	@Override
	public ResponseDto addDepartment(DepartmentsDto dto) {
		Departments department = new Departments();
		BeanUtils.copyProperties(dto, department);
		departmentrepository.save(department);
		ResponseDto response = new ResponseDto();
		response.setResponse("Department is added successfully");
		return response;

	}

	// Adds a new employee.
	@Override
	public ResponseDto addEmployee(EmployeesDto dto) {
		Employees employee = new Employees();
		BeanUtils.copyProperties(dto, employee);
		employeerepository.save(employee);
		ResponseDto response = new ResponseDto();
		response.setResponse("Employee is added successfully");
		return response;

	}

	// Retrieves a list of experienced employees based on the number of years.
	@Override
	public List<EmployeesDto> getExperiencedEmployeeByYear(Integer noofyear) {
		LocalDate today = LocalDate.now();
		LocalDate targetDate = today.minusYears(noofyear);
		List<Employees> employeesList = employeerepository.findEmployeeJoinedByNYear(targetDate.getYear());
		List<EmployeesDto> employeesDtoList = new ArrayList<>();
		for (Employees emp : employeesList) {
			EmployeesDto empdto = new EmployeesDto();
			BeanUtils.copyProperties(emp, empdto);
			employeesDtoList.add(empdto);
		}
		return employeesDtoList;

	}

	// Retrieves the count of employees by gender.
	@Override
	public Integer getEmployeeCountByGender(String gender) {

		return employeerepository.findCountEmployeeByGender(gender);
	}

	// Updates the salary based on the employee's rating and promotion status.
	@Override
	public ResponseDto updateSalaryByRating(RequestDto dto, Integer empno) {
		LocalDate currentdate = salaryrepository.findLatestDate(empno);
		if (currentdate != null) {
			Integer oldSalary = salaryrepository.findLatestSalary(currentdate, empno);
			Integer updatedSalary = 0;
			Integer rating = dto.getRating();
			Boolean promoted = dto.getPromoted();

			/*
			 * if (rating == 1) { updatedSalary = oldSalary + 10000; } else if (rating == 2)
			 * { updatedSalary = oldSalary + 7000; ; } else if (rating == 3) { updatedSalary
			 * = oldSalary + 5000; } else { updatedSalary = oldSalary + 0; }
			 */
			switch (rating) {
			case 1:
				updatedSalary = oldSalary + 10000;
				if (promoted) {
					updatedSalary = updatedSalary + 100000;
				}
				break;
			case 2:
				updatedSalary = oldSalary + 7000;
				if(promoted) {
					updatedSalary = updatedSalary + 100000;
				}
				break;
			case 3:
				updatedSalary = oldSalary + 5000;
				if(promoted) {
					updatedSalary = updatedSalary + 100000;
				}
				break;
			default:
				updatedSalary = oldSalary + 0;
				if(promoted) {
					updatedSalary = updatedSalary + 100000;
				}
				break;
			}

			Salaries salaries = new Salaries();
			Employees e = new Employees(empno);
			salaries.setEmployees(e);
			salaries.setFromDate(currentdate);
			salaries.setToDate(currentdate.plusYears(1));
			salaries.setSalary(updatedSalary);
			salaryrepository.save(salaries);
			ResponseDto response = new ResponseDto();
			response.setResponse("Employee year wise salary details updated successfully.");
			return response;
		}
		throw new UniversalExceptionHandler("Employee Not Found");

	}

	// Deletes an employee based on the employee number.
	@Override
	public ResponseDto deleteEmployee(Integer empno) {
		Optional<Employees> employee = employeerepository.findById(empno);
		employeerepository.delete(employee.get());

		ResponseDto response = new ResponseDto();
		response.setResponse("Employee is deleted successfully.");
		return response;
	}

}
