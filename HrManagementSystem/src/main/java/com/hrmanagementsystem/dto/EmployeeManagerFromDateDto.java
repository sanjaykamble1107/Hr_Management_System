package com.hrmanagementsystem.dto;

import java.time.LocalDate;


import java.util.Objects;

public class EmployeeManagerFromDateDto {
	private Integer empNo;
	private  String firstName;
	private String lastName;
	private LocalDate hireDate;
	public Integer getEmpNo() {
		return empNo;
	}
	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDate getHireDate() {
		return hireDate;
	}
	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}
	@Override
	public int hashCode() {
		return Objects.hash(empNo, firstName, hireDate, lastName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeManagerFromDateDto other = (EmployeeManagerFromDateDto) obj;
		return Objects.equals(empNo, other.empNo) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(hireDate, other.hireDate) && Objects.equals(lastName, other.lastName);
	}
	@Override
	public String toString() {
		return "EmployeeManagerFromDateDto [empNo=" + empNo + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", hireDate=" + hireDate + "]";
	}
	

}
