package com.hrmanagementsystem.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "departments")
public class Departments implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "dept_no", columnDefinition = "char(4)")
//	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private String deptNo;
	@Column(name = "dept_name")
	private String deptName;

	public Departments() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Departments(String deptNo2) {
	this.deptNo=deptNo2;	
	}

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
		Departments other = (Departments) obj;
		return Objects.equals(deptName, other.deptName) && Objects.equals(deptNo, other.deptNo);
	}

	@Override
	public String toString() {
		return "Departments [deptNo=" + deptNo + ", deptName=" + deptName + "]";
	}
	

}
