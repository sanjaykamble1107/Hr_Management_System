package com.hrmanagementsystem.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.hrmanagementsystem.dto.GetEmployeeJoinedAfterHireDate;
import com.hrmanagementsystem.entity.Employees;
import com.hrmanagementsystem.entity.Gender;

public interface EmployeeRepository
		extends JpaRepository<Employees, Integer>, PagingAndSortingRepository<Employees, Integer> {
	List<Employees> findByFirstName(String firstName);

	List<Employees> findBylastName(String lastName);

	List<Employees> findByHireDate(LocalDate hireDate);

	List<Employees> findByBirthDate(LocalDate birthDate);

	List<Employees> findByGender(Gender gender, Pageable pageable);

	@Query(value = "SELECT e.emp_no as employeeNumber,e.first_name as firstName,e.last_name as lastName,"
			+ "e.hire_date as hireDate FROM employees e WHERE hire_date >=:date", nativeQuery = true)
	List<GetEmployeeJoinedAfterHireDate> findEmployeeByLastNYear(@Param("date") LocalDate date);

	@Query(value = "SELECT count(emp_no) from employees where hire_date >=:hire_date", nativeQuery = true)
	Integer findCountEmployeeByNYear(LocalDate hire_date);

	@Query(value = "SELECT count(emp_no) from employees where gender =:gender", nativeQuery = true)
	Integer findCountEmployeeByGender(String gender);

	@Query(value = "SELECT * FROM employees WHERE YEAR(hire_date)>=:year", nativeQuery = true)
	List<Employees> findEmployeeJoinedByNYear(Integer year);

	@Query(value = "SELECT count(emp_no) FROM employees WHERE YEAR(hire_date)>=:year", nativeQuery = true)
	Integer findCountEmployeeJoinedByNYear(Integer year);

}
