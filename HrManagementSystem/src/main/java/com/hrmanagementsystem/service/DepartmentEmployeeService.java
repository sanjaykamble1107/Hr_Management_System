package com.hrmanagementsystem.service;

import java.time.LocalDate;
import java.util.List;

import com.hrmanagementsystem.dto.DepartmentEmployeeDto;

public interface DepartmentEmployeeService {

	public String addDepartmentEmployee(DepartmentEmployeeDto dto);

	public List<DepartmentEmployeeDto> getAllDepartmentEmployee();

	public List<DepartmentEmployeeDto> getDepartmentEmployeeByDeptNoAndFromDate(String deptno, LocalDate fromdate);

	public DepartmentEmployeeDto getDepartmentEmployeeByEmpNoAndFromDate(Integer empno, LocalDate fromdate);

	DepartmentEmployeeDto getDepartmentEmployeeByEmpNoAndDeptNo(Integer empno, String deptno);

	public List<DepartmentEmployeeDto> getDepartmentEmployeeByEmpNoAndDeptNoAndFromDate(Integer empno, String deptno,
			LocalDate fromdate);

	public String updateDepartmentEmployeeByEmpNoAndDeptNo(DepartmentEmployeeDto dto, Integer empno, String deptno);

	public String updateDepartmentEmployeeByEmpNoAndFromDate(DepartmentEmployeeDto dto, Integer empno,
			LocalDate fromdate);

	public List<DepartmentEmployeeDto> updateDepartmentEmployeeByDeptNoAndFromDate(DepartmentEmployeeDto dto,
			String deptno, LocalDate fromdate);

	public String updateDepartmentEmployeeByEmpNoAndDeptNoAndFromDate(DepartmentEmployeeDto dto, Integer empno,
			String deptno, LocalDate fromdate);
}
