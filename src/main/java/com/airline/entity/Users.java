package com.airline.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Users extends AuditLogs{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long userid;
	
	private String username;
	private String password;
	private String role;
	
	
	private String firstName;
	private String lastName;
	private String gender;
	
	private Date dateofBirth;
	
	@Transient
	private String DOB;
	
	private Boolean isActive=true;

	
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "forumId") private Captain captain;
	 */
	
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", password=" + password + ", role=" + role
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender + ", dateofBirth="
				+ dateofBirth + ", DOB=" + DOB + ", isActive=" + isActive + ", userasCaptain=" + userasCaptain + "]";
	}

	public Date getDateofBirth() {
		return dateofBirth;
	}

	public void setDateofBirth(Date dateofBirth) {
		this.dateofBirth = dateofBirth;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	public String getUserasCaptain() {
		return userasCaptain;
	}

	public void setUserasCaptain(String userasCaptain) {
		this.userasCaptain = userasCaptain;
	}
	@Transient
	private String userasCaptain;
	
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	
	
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
