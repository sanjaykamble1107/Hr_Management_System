package com.hrmanagementsystem.dto;

import java.time.LocalDate;
import java.util.Objects;

public class EmployeeDesignationProjectionDto {

	private Integer empNo;
	private String firstName;
	private String lastName;
	private LocalDate fromDate;
	private LocalDate toDate;
	private String designation;
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
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(designation, empNo, firstName, fromDate, lastName, toDate);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeDesignationProjectionDto other = (EmployeeDesignationProjectionDto) obj;
		return Objects.equals(designation, other.designation) && Objects.equals(empNo, other.empNo)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(fromDate, other.fromDate)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(toDate, other.toDate);
	}
	@Override
	public String toString() {
		return "EmployeeDesignationProjectionDto [empNo=" + empNo + ", firstName=" + firstName + ", lastName="
				+ lastName + ", fromDate=" + fromDate + ", toDate=" + toDate + ", designation=" + designation + "]";
	}
	
	
}
