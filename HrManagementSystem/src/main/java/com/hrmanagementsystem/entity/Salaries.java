package com.hrmanagementsystem.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "salaries")
@IdClass(CompositeSalaries.class)
public class Salaries implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@ManyToOne
	@JoinColumn(name = "emp_no")
	private Employees employees;
	@Column(name = "salary")
	private Integer salary;
	@Id
	@Column(name = "from_date", columnDefinition = "Date")
	private LocalDate fromDate;
	@Column(name = "to_date", columnDefinition = "Date")
	private LocalDate toDate;

	
	
	
	public Salaries(Employees employees) {
		super();
		this.employees = employees;
	}

	public Employees getEmployees() {
		return employees;
	}

	public void setEmployees(Employees employees) {
		this.employees = employees;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	public Salaries() {
		super();
		// TODO Auto-generated constructor stub
	}

}

class CompositeSalaries {

	private Employees employees;
	private LocalDate fromDate;
	public CompositeSalaries() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CompositeSalaries(Employees employees, LocalDate fromDate) {
		super();
		this.employees = employees;
		this.fromDate = fromDate;
	}
	public Employees getEmployees() {
		return employees;
	}
	public void setEmployees(Employees employees) {
		this.employees = employees;
	}
	public LocalDate getFromDate() {
		return fromDate;
	}
	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}
	@Override
	public int hashCode() {
		return Objects.hash(employees, fromDate);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompositeSalaries other = (CompositeSalaries) obj;
		return Objects.equals(employees, other.employees) && Objects.equals(fromDate, other.fromDate);
	}

	
}
