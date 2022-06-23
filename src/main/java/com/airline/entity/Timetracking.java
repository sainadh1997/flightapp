package com.airline.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Timetracking extends AuditLogs{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	private Date date;
	private String captainid;
	private String onduty;
	private String offduty;
	private String schedulearraival;
	private String scheduledeparture;
	
	@Transient
	private String dutyDate;
	
	private Boolean isActive=true;
	
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public String getCaptainName() {
		return captainName;
	}
	public void setCaptainName(String captainName) {
		this.captainName = captainName;
	}
	@Transient
	private String captainName;
	
	
		public String getCaptainid() {
		return captainid;
	}
	public void setCaptainid(String captainid) {
		this.captainid = captainid;
	}
	public String getSchedulearraival() {
		return schedulearraival;
	}
	public void setSchedulearraival(String schedulearraival) {
		this.schedulearraival = schedulearraival;
	}
	public String getScheduledeparture() {
		return scheduledeparture;
	}
	public void setScheduledeparture(String scheduledeparture) {
		this.scheduledeparture = scheduledeparture;
	}
		public String getDutyDate() {
		return dutyDate;
	}
	public void setDutyDate(String dutyDate) {
		this.dutyDate = dutyDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getOnduty() {
		return onduty;
	}
	public void setOnduty(String onduty) {
		this.onduty = onduty;
	}
	public String getOffduty() {
		return offduty;
	}
	public void setOffduty(String offduty) {
		this.offduty = offduty;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
