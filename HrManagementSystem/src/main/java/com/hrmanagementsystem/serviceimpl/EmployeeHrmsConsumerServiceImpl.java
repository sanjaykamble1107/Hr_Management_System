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

import com.hrmanagementsystem.dto.DepartmentReportDto;
import com.hrmanagementsystem.dto.DesignationReportDto;
import com.hrmanagementsystem.dto.EmployeeDTO;
import com.hrmanagementsystem.dto.EmployeeDesignationProjectionDto;
import com.hrmanagementsystem.dto.EmployeeManagerFromDateDto;
import com.hrmanagementsystem.dto.EmployeesDto;
import com.hrmanagementsystem.dto.ManagerDTO;
import com.hrmanagementsystem.dto.ResponseDto;
import com.hrmanagementsystem.entity.DepartmentEmployee;
import com.hrmanagementsystem.entity.DepartmentManager;
import com.hrmanagementsystem.entity.Employees;
import com.hrmanagementsystem.entity.Titles;
import com.hrmanagementsystem.exception.UniversalExceptionHandler;
import com.hrmanagementsystem.repository.DepartmentEmployeeRepository;
import com.hrmanagementsystem.repository.DepartmentManagerRepository;
import com.hrmanagementsystem.repository.DepartmentsRepository;
import com.hrmanagementsystem.repository.EmployeeRepository;
import com.hrmanagementsystem.repository.TitlesRepository;
import com.hrmanagementsystem.service.EmployeeHrmsConsumerService;

@Service
public class EmployeeHrmsConsumerServiceImpl implements EmployeeHrmsConsumerService {

	// Repositories for various entities

	private final DepartmentManagerRepository departmentmanagerrepository;
	private final TitlesRepository titlerepository;
	private final EmployeeRepository employeerepository;
	private final DepartmentEmployeeRepository departmentemployeerepository;

	private final DepartmentsRepository departmentrepository;

	// Constructor injection of repositories
	@Autowired
	public EmployeeHrmsConsumerServiceImpl(DepartmentManagerRepository departmentmanagerrepository,
			TitlesRepository titlerepository, EmployeeRepository employeerepository,
			DepartmentEmployeeRepository departmentemployeerepository, DepartmentsRepository departmentrepository) {

		this.departmentmanagerrepository = departmentmanagerrepository;
		this.titlerepository = titlerepository;
		this.employeerepository = employeerepository;
		this.departmentemployeerepository = departmentemployeerepository;

		this.departmentrepository = departmentrepository;
	}



	// Retrieves a list of managers based on the specified from date.
	@Override
	public List<EmployeeManagerFromDateDto> getManagers(LocalDate fromdate) {
		List<DepartmentManager> deptMgrList = departmentmanagerrepository.findByFromDate(fromdate);
		List<EmployeeManagerFromDateDto> emplist = new ArrayList<>();
		for (DepartmentManager deptmgr : deptMgrList) {
			EmployeeManagerFromDateDto tempobj = new EmployeeManagerFromDateDto();
			tempobj.setEmpNo(deptmgr.getEmployees().getEmpNo());
			tempobj.setFirstName(deptmgr.getEmployees().getFirstName());
			tempobj.setLastName(deptmgr.getEmployees().getLastName());
			tempobj.setHireDate(deptmgr.getEmployees().getHireDate());
			emplist.add(tempobj);
		}
		return emplist;
	}

	// Retrieves a list of all designations.
	@Override
	public List<String> getDesignations() {
		List<String> titleList = titlerepository.findAllTitles();
		return titleList;
	}

	// Updates the last name of an employee.
	@Override
	public ResponseDto updateEmployeeLastName(EmployeesDto empdto, Integer empno) {
		Optional<Employees> employee = employeerepository.findById(empno);
         if(!employee.isEmpty()) {
		employee.get().setLastName(empdto.getLastName());
		employeerepository.save(employee.get());
		ResponseDto response = new ResponseDto();
		response.setResponse("LastName updated as " + employee.get().getLastName());
		return response;
         }

         throw new UniversalExceptionHandler("Employee Not Found");
	}

	// Retrieves a list of employees with the specified designation.
	@Override
	public List<EmployeeDesignationProjectionDto> getEmployeeDesignation(String title, Pageable pageable) {
		Page<Titles> titlesList = titlerepository.findByTitle(title, pageable);
		List<EmployeeDesignationProjectionDto> dtoList = new ArrayList<>();
		for (Titles titles : titlesList) {
			EmployeeDesignationProjectionDto dto = new EmployeeDesignationProjectionDto();
			BeanUtils.copyProperties(titles, dto);
			dto.setEmpNo(titles.getEmployees().getEmpNo());
			dto.setFirstName(titles.getEmployees().getFirstName());
			dto.setLastName(titles.getEmployees().getLastName());
			dto.setDesignation(titles.getTitle());
			dtoList.add(dto);
		}

		return dtoList;
	}

	// Retrieves a list of employees within the age range of 50 to 60 years.
	@Override
	public List<Employees> getMidAgeEmployees(Pageable pageable) {
		Page<Employees> employeesList = employeerepository.findAll(pageable);
		List<Employees> midAgeEmployees = new ArrayList<>();

		LocalDate currentDate = LocalDate.now();
		LocalDate fiftyYearsAgo = currentDate.minusYears(50);
		LocalDate sixtyYearsAgo = currentDate.minusYears(60);

		for (Employees employee : employeesList) {
			LocalDate birthDate = employee.getBirthDate();
			if (birthDate.isAfter(sixtyYearsAgo) && birthDate.isBefore(fiftyYearsAgo)) {
				midAgeEmployees.add(employee);
			}
		}

		return midAgeEmployees;

	}

	// Retrieves a list of employees working in a specific department during a
	// specific year.
	@Override
	public List<EmployeeDTO> getEmployeeWorkingSpecificDept(String deptno, Integer year ,Pageable pageable) {

		Page<DepartmentEmployee> deptemplist = departmentemployeerepository.findEmployeeInDeptInSpecificYear(deptno,
				year,pageable);
		List<EmployeeDTO> dtolist = new ArrayList<>();
		for (DepartmentEmployee emp : deptemplist) {
			EmployeeDTO dto = new EmployeeDTO();
			BeanUtils.copyProperties(emp, dto);
			dto.setEmpNo(emp.getEmployees().getEmpNo());
			dto.setFirstName(emp.getEmployees().getFirstName());
			dto.setLastName(emp.getEmployees().getLastName());
			dto.setDeptName(emp.getDepartments().getDeptName());
			dtolist.add(dto);
		}
		return dtolist;
	}

	// Retrieves a list of managers along with their details.
	@Override
	public List<ManagerDTO> getEmployeeManager() {
		List<DepartmentManager> managerlist = departmentmanagerrepository.findAll();
		List<ManagerDTO> dtoList = new ArrayList<>();
		for (DepartmentManager manager : managerlist) {
			ManagerDTO dto = new ManagerDTO();
			dto.setEmpNo(manager.getEmployees().getEmpNo());
			dto.setFirstName(manager.getEmployees().getFirstName());
			dto.setLastName(manager.getEmployees().getLastName());
			dto.setBirthDate(manager.getEmployees().getBirthDate());
			dto.setDeptName(manager.getDepartments().getDeptName());
			dtoList.add(dto);
		}
		return dtoList;
	}



	// Retrieves a list of designation details
	@Override
	public List<DesignationReportDto> getDesignationDetails() {
		return titlerepository.getDesignationReport();

	}

	// Retrieves a list of department details.
	@Override
	public List<DepartmentReportDto> getDepartmentDetails() {
		return departmentrepository.getDepartmentSalaryReport();

	}

}
