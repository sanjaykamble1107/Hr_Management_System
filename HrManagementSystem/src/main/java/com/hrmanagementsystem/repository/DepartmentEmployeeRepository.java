package com.hrmanagementsystem.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hrmanagementsystem.entity.CompositeDepartmentEmployee;
import com.hrmanagementsystem.entity.DepartmentEmployee;

public interface DepartmentEmployeeRepository extends JpaRepository<DepartmentEmployee, CompositeDepartmentEmployee> {

	@Query(value = "SELECT * FROM dept_emp WHERE dept_no = :deptno and from_date = :fromdate", nativeQuery = true)
	List<DepartmentEmployee> findByDeptNoAndFromDate(String deptno, LocalDate fromdate);

	@Query(value = "SELECT * FROM dept_emp WHERE emp_no = :empno and from_date = :fromdate", nativeQuery = true)
	DepartmentEmployee findByEmpNoAndFromDate(Integer empno, LocalDate fromdate);

	@Query(value = "SELECT * FROM dept_emp WHERE emp_no = :empno and dept_no = :deptno", nativeQuery = true)
	DepartmentEmployee findByEmpNoAndDeptNo(Integer empno, String deptno);

	@Query(value = "SELECT * FROM dept_emp WHERE emp_no = :empno and dept_no = :deptno and from_date = :fromdate", nativeQuery = true)
	List<DepartmentEmployee> findByEmpNoAndDeptNoAndFromDate(Integer empno, String deptno, LocalDate fromdate);

	@Query(value = "SELECT * FROM dept_emp WHERE dept_no = :dept_no AND YEAR(from_date) =:year", nativeQuery = true)
	Page<DepartmentEmployee> findEmployeeInDeptInSpecificYear(String dept_no, Integer year,Pageable pageable);
}