package com.hrmanagementsystem.dto;

import java.time.LocalDate;
import java.util.Objects;

public class ManagerDTO {

	private Integer empNo;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private String deptName;
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
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	@Override
	public int hashCode() {
		return Objects.hash(birthDate, deptName, empNo, firstName, lastName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ManagerDTO other = (ManagerDTO) obj;
		return Objects.equals(birthDate, other.birthDate) && Objects.equals(deptName, other.deptName)
				&& Objects.equals(empNo, other.empNo) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName);
	}
	@Override
	public String toString() {
		return "ManagerDTO [empNo=" + empNo + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate="
				+ birthDate + ", deptName=" + deptName + "]";
	}
	
	
	
	
}
