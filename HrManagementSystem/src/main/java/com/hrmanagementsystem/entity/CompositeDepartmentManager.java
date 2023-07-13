package com.hrmanagementsystem.entity;

import java.util.Objects;

public class CompositeDepartmentManager {

	private Employees employees;
	private Departments departments;
	
	
	public CompositeDepartmentManager() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CompositeDepartmentManager(Employees employees, Departments departments) {
		super();
		this.employees = employees;
		this.departments = departments;
	}


	public Employees getEmployees() {
		return employees;
	}


	public void setEmployees(Employees employees) {
		this.employees = employees;
	}


	public Departments getDepartments() {
		return departments;
	}


	public void setDepartments(Departments departments) {
		this.departments = departments;
	}


	@Override
	public int hashCode() {
		return Objects.hash(departments, employees);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompositeDepartmentManager other = (CompositeDepartmentManager) obj;
		return Objects.equals(departments, other.departments) && Objects.equals(employees, other.employees);
	}


	@Override
	public String toString() {
		return "CompositeDepartmentManager [employees=" + employees + ", departments=" + departments + "]";
	}
	
	
}

