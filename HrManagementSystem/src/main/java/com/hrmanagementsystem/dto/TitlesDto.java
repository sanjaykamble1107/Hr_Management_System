package com.hrmanagementsystem.dto;


import java.time.LocalDate;
import java.util.Objects;

import jakarta.validation.constraints.NotNull;

public class TitlesDto {
	@NotNull(message = "Title cannot be null")
	private String title;
	@NotNull(message = "FromDate cannot be null")
	private LocalDate fromDate;
	private LocalDate toDate;
	//private EmployeesDto employees;
	@NotNull(message = "EmpNo cannot be null")
	private Integer empNo;
	

	public Integer getEmpNo() {
		return empNo;
	}

	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}

	public TitlesDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*public EmployeesDto getEmployees() {
		return employees;
	}
	public void setEmployees(EmployeesDto employees) {
		this.employees = employees;
	}*/

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
	public int hashCode() {
		return Objects.hash(empNo, fromDate, title, toDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TitlesDto other = (TitlesDto) obj;
		return Objects.equals(empNo, other.empNo) && Objects.equals(fromDate, other.fromDate)
				&& Objects.equals(title, other.title) && Objects.equals(toDate, other.toDate);
	}

	@Override
	public String toString() {
		return "TitlesDto [title=" + title + ", fromDate=" + fromDate + ", toDate=" + toDate + ", empNo=" + empNo + "]";
	}

	

	

}
