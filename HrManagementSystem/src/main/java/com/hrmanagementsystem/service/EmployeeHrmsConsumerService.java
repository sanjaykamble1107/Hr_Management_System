package com.hrmanagementsystem.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.hrmanagementsystem.dto.DepartmentReportDto;
import com.hrmanagementsystem.dto.DesignationReportDto;
import com.hrmanagementsystem.dto.EmployeeDTO;
import com.hrmanagementsystem.dto.EmployeeDesignationProjectionDto;
import com.hrmanagementsystem.dto.EmployeeManagerFromDateDto;
import com.hrmanagementsystem.dto.EmployeesDto;
import com.hrmanagementsystem.dto.ManagerDTO;
import com.hrmanagementsystem.dto.ResponseDto;
import com.hrmanagementsystem.entity.Employees;

public interface EmployeeHrmsConsumerService {



	List<EmployeeManagerFromDateDto> getManagers(LocalDate fromdate);

	List<String> getDesignations();

	ResponseDto updateEmployeeLastName(EmployeesDto empdto, Integer empno);

	List<EmployeeDesignationProjectionDto> getEmployeeDesignation(String title,Pageable pageable);

	List<Employees> getMidAgeEmployees(Pageable pageable);

	List<EmployeeDTO> getEmployeeWorkingSpecificDept(String deptno, Integer year,Pageable pageable);

	List<ManagerDTO> getEmployeeManager();

	List<DesignationReportDto> getDesignationDetails();

	List<DepartmentReportDto> getDepartmentDetails();

}