package com.hrmanagementsystem.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.hrmanagementsystem.entity.Salaries;

public interface SalariesRepository
		extends JpaRepository<Salaries, LocalDate>, PagingAndSortingRepository<Salaries, LocalDate> {

	List<Salaries> findByFromDate(LocalDate fromdate);

	@Query(value = "SELECT * FROM Salaries  WHERE emp_no = :empno and from_date = :fromdate", nativeQuery = true)
	Salaries findByEmpNoAndFromDate(Integer empno, LocalDate fromdate);

	@Query(value = "SELECT * FROM Salaries  WHERE emp_no = :empno ", nativeQuery = true)
	List<Salaries> findByEmpNo(Integer empno);

	@Query(value = "SELECT * FROM Salaries  WHERE salary BETWEEN  :minsalary AND  :maxsalary ", nativeQuery = true)
	Page<Salaries> findSalariesBetweenMaxAndMin(Integer minsalary, Integer maxsalary, Pageable pageable);

	@Query(value = "SELECT  MAX(to_date) FROM salaries WHERE emp_no = :empno ", nativeQuery = true)
	LocalDate findLatestDate(Integer empno);

	@Query(value = "SELECT  salary FROM salaries WHERE to_date = :todate AND emp_no= :empno ", nativeQuery = true)
	Integer findLatestSalary(LocalDate todate, Integer empno);

}
