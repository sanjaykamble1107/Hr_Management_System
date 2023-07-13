package com.hrmanagementsystem.entity;

import java.time.LocalDate;
import java.util.Objects;

public class CompositeTitles{
	private Employees employees;
	private String title;
	private LocalDate fromDate;
	
	public CompositeTitles() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CompositeTitles(Employees employees, String title, LocalDate fromDate) {
		super();
		this.employees = employees;
		this.title = title;
		this.fromDate = fromDate;
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
	@Override
	public int hashCode() {
		return Objects.hash(employees, fromDate, title);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompositeTitles other = (CompositeTitles) obj;
		return Objects.equals(employees, other.employees) && Objects.equals(fromDate, other.fromDate)
				&& Objects.equals(title, other.title);
	}
	@Override
	public String toString() {
		return "CompositeTitles [employees=" + employees + ", title=" + title + ", fromDate=" + fromDate + "]";
	}
		
}
