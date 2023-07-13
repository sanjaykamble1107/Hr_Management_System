package com.hrmanagementsystem.dto;

import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;

public class SalariesDto {
    @NotNull(message = "EmpNo cannot be null")
	private Integer empNo;
    @NotNull(message = "Salary cannot be null")
	private Integer salary;
	@JsonFormat(shape = JsonFormat.Shape.STRING ,pattern = "yyyy-MM-dd")
	@NotNull(message = "FromDate cannot be null")
	private LocalDate fromDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING ,pattern = "yyyy-MM-dd")
	private LocalDate toDate;

	public SalariesDto() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	public SalariesDto(Integer empNo, Integer salary, LocalDate fromDate, LocalDate toDate) {
		super();
		this.empNo = empNo;
		this.salary = salary;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	public Integer getEmpNo() {
		return empNo;
	}

	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
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

	@Override
	public int hashCode() {
		return Objects.hash(empNo, fromDate, salary, toDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SalariesDto other = (SalariesDto) obj;
		return Objects.equals(empNo, other.empNo) && Objects.equals(fromDate, other.fromDate)
				&& Objects.equals(salary, other.salary) && Objects.equals(toDate, other.toDate);
	}

	@Override
	public String toString() {
		return "SalariesDto [empNo=" + empNo + ", salary=" + salary + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ "]";
	}

}
