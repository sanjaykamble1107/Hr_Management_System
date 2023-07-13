package com.hrmanagementsystem.dto;

import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hrmanagementsystem.entity.Gender;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;


public class EmployeesDto {
	
	private Integer empNo;
	@JsonFormat(shape = JsonFormat.Shape.STRING ,pattern = "yyyy-MM-dd")
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "BithDate cannot be null")
	@Past
	private LocalDate birthDate;
	private String firstName;
	private String lastName;
	private Gender gender;
	@JsonFormat(shape = JsonFormat.Shape.STRING ,pattern = "yyyy-MM-dd")
	@NotNull(message = "HireDate cannot be null")
	private LocalDate hireDate; 
//	private Set<TitlesDto> titles;

	public EmployeesDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*public Set<TitlesDto> getTitles() {
		return titles;
	}

	public void setTitles(Set<TitlesDto> titles) {
		this.titles = titles;
	}*/

	public Integer getEmpNo() {
		return empNo;
	}

	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public LocalDate getHireDate() {
		return hireDate;
	}

	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthDate, empNo, firstName, gender, hireDate, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeesDto other = (EmployeesDto) obj;
		return Objects.equals(birthDate, other.birthDate) && Objects.equals(empNo, other.empNo)
				&& Objects.equals(firstName, other.firstName) && gender == other.gender
				&& Objects.equals(hireDate, other.hireDate) && Objects.equals(lastName, other.lastName);
	}

	@Override
	public String toString() {
		return "EmployeesDto [empNo=" + empNo + ", birthDate=" + birthDate + ", firstName=" + firstName + ", lastName="
				+ lastName + ", gender=" + gender + ", hireDate=" + hireDate + "]";
	}

	

	

}
