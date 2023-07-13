package com.hrmanagementsystem.dto;

import java.util.Objects;

import jakarta.validation.constraints.NotBlank;

public class DepartmentsDto {
	@NotBlank(message = "DeptNo cannot be blank")
	private String deptNo;

	private String deptName;

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(deptName, deptNo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DepartmentsDto other = (DepartmentsDto) obj;
		return Objects.equals(deptName, other.deptName) && Objects.equals(deptNo, other.deptNo);
	}

	@Override
	public String toString() {
		return "DepartmentsDto [deptNo=" + deptNo + ", deptName=" + deptName + "]";
	}

}
