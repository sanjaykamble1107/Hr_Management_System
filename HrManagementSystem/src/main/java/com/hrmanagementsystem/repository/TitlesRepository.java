package com.hrmanagementsystem.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.hrmanagementsystem.dto.DesignationReportDto;
import com.hrmanagementsystem.entity.CompositeTitles;
import com.hrmanagementsystem.entity.Titles;

public interface TitlesRepository
		extends JpaRepository<Titles, CompositeTitles>, PagingAndSortingRepository<Titles, CompositeTitles> {

	@Query(value = "SELECT * FROM Titles  WHERE emp_no = :empno and from_date = :fromdate and title = :title", nativeQuery = true)
	Titles findByEmpNoAndFromDateAndTitle(Integer empno, LocalDate fromdate, String title);

	List<Titles> findByFromDate(LocalDate fromdate);

	Page<Titles> findByTitle(String title, Pageable pageable);

	@Query(value = "SELECT * FROM Titles  WHERE emp_no = :empno ", nativeQuery = true)
	List<Titles> findByEmpNo(Integer empno);

	@Query(value = "SELECT * FROM Titles  WHERE title = :title and from_date = :fromdate ", nativeQuery = true)
	List<Titles> findByTitleAndFromDate(String title, LocalDate fromdate);

	@Query(value = "SELECT * FROM Titles  WHERE emp_no = :empno and from_date = :fromdate ", nativeQuery = true)
	List<Titles> findByEmpNoAndFromDate(Integer empno, LocalDate fromdate);

	@Query(value = "SELECT * FROM Titles  WHERE emp_no = :empno and title = :title  ", nativeQuery = true)
	List<Titles> findByEmpNoAndTitle(Integer empno, String title);

	@Query(value = "SELECT DISTINCT t.title FROM Titles t", nativeQuery = true)
	List<String> findAllTitles();

	@Query(value = "SELECT titles.title as designation, AVG(salaries.salary) as averageSalary, MAX(salaries.salary) as maxSalary, MIN(salaries.salary) as minSalary FROM titles INNER JOIN employees ON titles.emp_no = employees.emp_no INNER JOIN salaries ON employees.emp_no = salaries.emp_no GROUP BY titles.title ", nativeQuery = true)
	List<DesignationReportDto> getDesignationReport();

}
