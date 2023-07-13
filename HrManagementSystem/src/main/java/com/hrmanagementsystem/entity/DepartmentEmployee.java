package com.hrmanagementsystem.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "dept_emp")
@IdClass(CompositeDepartmentEmployee.class)
public class DepartmentEmployee implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@ManyToOne
	@JoinColumn(name = "emp_no")
	private Employees employees;
	@Column(name = "from_date", columnDefinition = "Date")
	private LocalDate fromDate;
	@Column(name = "to_date", columnDefinition = "Date")
	private LocalDate toDate;
	@Id
	@ManyToOne
	@JoinColumn(name = "dept_no")
	private Departments departments;
	

	

	public Departments getDepartments() {
		return departments;
	}

	public void setDepartments(Departments departments) {
		this.departments = departments;
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

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	@Override
	public String toString() {
		return "DepartmentEmployee [employees=" + employees + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", departments=" + departments + "]";
	}

}
