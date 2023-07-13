package com.hrmanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hrmanagementsystem.dto.DepartmentReportDto;
import com.hrmanagementsystem.entity.Departments;

public interface DepartmentsRepository extends JpaRepository<Departments, String> {
	Departments findByDeptName(String deptName);

	@Query(value = "SELECT d.dept_no as deptNo, AVG(s.salary) as avgSalary, MAX(s.salary) as maxSalary, MIN(s.salary)as minSalary FROM departments d JOIN dept_emp de ON d.dept_no = de.dept_no JOIN salaries s ON de.emp_no = s.emp_no GROUP BY de.dept_no", nativeQuery = true)
	List<DepartmentReportDto> getDepartmentSalaryReport();
}
