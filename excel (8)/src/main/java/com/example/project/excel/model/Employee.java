package com.example.project.excel.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="EMPLOYEE")
public class Employee {
	
	@Id
	@Column(name="EMPID")
	private int empId;
	@Column(name="EMPEMAIL")
	private String empEmail;
	@Column(name="NAME")
	private String name;
	@Temporal(TemporalType.DATE)
	//@JsonFormat(pattern="MM-dd-yyyy")
	@Column(name="DOJ")
	private Date doj;
	@Column(name="LOB")
	private String lob;
	@Column(name="STREAM")
	private String stream;
	@Column(name="ROLE")
	private String role;
	@Column(name="LOCATION")
	private String location;
	@Column(name="MANAGEREMAIL")
	private String managerEmail;	
	
	public Employee() {
		
	}

	public Employee(int empId, String empEmail, String name, @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) Date doj, String lob, String stream, String role,
			String location, String managerEmail) {
		super();
		this.empId = empId;
		this.empEmail = empEmail;
		this.name = name;
		this.doj = doj;
		this.lob = lob;
		this.stream = stream;
		this.role = role;
		this.location = location;
		this.managerEmail = managerEmail;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public String getLob() {
		return lob;
	}

	public void setLob(String lob) {
		this.lob = lob;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getManagerEmail() {
		return managerEmail;
	}

	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}
	
	

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empEmail=" + empEmail + ", name=" + name + ", doj=" + doj + ", lob="
				+ lob + ", stream=" + stream + ", role=" + role + ", location=" + location + ", managerEmail="
				+ managerEmail + "]";
	}
	
	

}