package com.hrmanagementsystem.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hrmanagementsystem.entity.CompositeDepartmentManager;
import com.hrmanagementsystem.entity.DepartmentManager;

public interface DepartmentManagerRepository extends JpaRepository<DepartmentManager, CompositeDepartmentManager> {

	@Query(value = "SELECT * FROM dept_manager WHERE emp_no = :empno and dept_no = :deptno", nativeQuery = true)
	DepartmentManager findByEmpNoAndDeptNo(Integer empno, String deptno);

	@Query(value = "SELECT * FROM dept_manager WHERE dept_no = :deptno and from_date = :fromdate", nativeQuery = true)
	List<DepartmentManager> findByDeptNoAndFromDate(String deptno, LocalDate fromdate);

	@Query(value = "SELECT * FROM dept_manager WHERE emp_no = :empno and from_date = :fromdate", nativeQuery = true)
	List<DepartmentManager> findByEmpNoAndFromDate(Integer empno, LocalDate fromdate);

	@Query(value = "SELECT * FROM dept_manager WHERE emp_no = :empno and dept_no = :deptno and from_date = :fromdate", nativeQuery = true)
	List<DepartmentManager> findByEmpNoAndDeptNoAndFromDate(Integer empno, String deptno, LocalDate fromdate);

	@Query(value = "SELECT * FROM dept_manager WHERE from_date = :fromdate", nativeQuery = true)
	List<DepartmentManager> findByFromDate(LocalDate fromdate);

}
