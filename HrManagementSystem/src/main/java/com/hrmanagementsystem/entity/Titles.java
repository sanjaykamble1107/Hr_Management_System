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
@Table(name = "titles")
@IdClass(CompositeTitles.class)
public class Titles implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "title")
	private String title;
	@Id
	@Column(name = "from_date" ,columnDefinition = "Date")
	private LocalDate fromDate;
	@Column(name = "to_date" ,columnDefinition = "Date")
	private LocalDate toDate;
	
	@Id
	@ManyToOne
	@JoinColumn(name="emp_no")
	private Employees employees;

	public Titles() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employees getEmployees() {
		return employees;
	}

	public void setEmployees(Employees employees) {
		this.employees = employees;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
		return "Titles [title=" + title + ", fromDate=" + fromDate + ", toDate=" + toDate + ", employees=" + employees
				+ "]";
	}
	
	
}
	


