package com.hrmanagementsystem.entity;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity
@Table(name = "employees")

public class Employees implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "emp_no")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//	@GeneratedValue
	private Integer id;
	@Column(name = "birth_date" ,columnDefinition = "Date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	private Gender gender;
	@Column(name = "hire_date" ,columnDefinition = "Date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private LocalDate hireDate;
	/*@JsonIgnore
	
	@OneToMany(mappedBy = "employees" ,cascade=CascadeType.ALL)
	private Set<Titles> titles;*/

	public Employees() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	/*public Set<Titles> getTitles() {
		return titles;
	}


	public void setTitles(Set<Titles> titles) {
		this.titles = titles;
	}*/


	public Employees(Integer empNo) {
		this.id=empNo;}


	public Integer getEmpNo() {
		return id;
	}

	public void setEmpNo(Integer empNo) {
		this.id = empNo;
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
	public String toString() {
		return "Employees [id=" + id + ", birthDate=" + birthDate + ", firstName=" + firstName + ", lastName="
				+ lastName + ", gender=" + gender + ", hireDate=" + hireDate + "]";
	}

}
