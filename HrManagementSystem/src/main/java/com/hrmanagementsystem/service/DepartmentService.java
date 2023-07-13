package com.hrmanagementsystem.service;

import java.util.List;

import com.hrmanagementsystem.dto.DepartmentsDto;

public interface DepartmentService {
 public String addDepartment(DepartmentsDto departmentdto);
 public List<DepartmentsDto> getAllDepartments();
 public DepartmentsDto getDepartmentByNo(String deptno);
 public DepartmentsDto getDepartmentByName(String deptname);
 public String updateDepartmentByNo(DepartmentsDto deptdto, String deptno);
 public String updateDepartmentByName(DepartmentsDto deptdto,String deptname);
 
}
