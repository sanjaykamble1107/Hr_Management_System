package com.hrmanagementsystem.service;

import java.time.LocalDate;
import java.util.List;

import com.hrmanagementsystem.dto.DepartmentManagerDto;

public interface DepartmentManagerService {

	List<DepartmentManagerDto> getAllDepartmentManager();

	DepartmentManagerDto getDepartmentManagerByEmpNoAndDeptNo(Integer empno, String deptno);

	List<DepartmentManagerDto> getDepartmentManagerByDeptNoAndFromDate(String deptno, LocalDate fromdate);

	List<DepartmentManagerDto> getDepartmentManagerByEmpNoAndFromDate(Integer empno, LocalDate fromdate);

	List<DepartmentManagerDto> getDepartmentManagerByEmpNoAndDeptNoAndFromDate(Integer empno, String deptno,
			LocalDate fromdate);

	String addDepartmentManager(DepartmentManagerDto dto);

	String updateDepartmentManagerByEmpNoAndDeptNo(DepartmentManagerDto dto, Integer empno, String deptno);

	String updateDepartmentManagerByEmpNoAndDeptNoAndFromDate(DepartmentManagerDto dto, Integer empno, String deptno,
			LocalDate fromdate);

	List<DepartmentManagerDto> updateDepartmentManagerByDeptNoAndFromDate(DepartmentManagerDto dto, String deptno,
			LocalDate fromdate);

	String updateDepartmentManagerByEmpNoAndFromDate(DepartmentManagerDto dto, Integer empno, LocalDate fromdate);

}
