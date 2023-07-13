package com.hrmanagementsystem.dto;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.validation.constraints.NotNull;

public class DepartmentEmployeeDto {
	@NotNull(message = "EmpNo cannot be null")
	private Integer empNo;
	@NotNull(message = "DeptNo cannot be null")
	private String deptNo;
	private LocalDate fromDate;
	private LocalDate toDate;

	public Integer getEmpNo() {
		return empNo;
	}

	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
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
		return Objects.hash(deptNo, empNo, fromDate, toDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DepartmentEmployeeDto other = (DepartmentEmployeeDto) obj;
		return Objects.equals(deptNo, other.deptNo) && Objects.equals(empNo, other.empNo)
				&& Objects.equals(fromDate, other.fromDate) && Objects.equals(toDate, other.toDate);
	}

	@Override
	public String toString() {
		return "DepartmentEmployeeDto [empNo=" + empNo + ", deptNo=" + deptNo + ", fromDate=" + fromDate + ", toDate="
				+ toDate + "]";
	}

}
