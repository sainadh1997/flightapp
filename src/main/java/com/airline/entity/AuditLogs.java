package com.airline.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.context.SecurityContextHolder;

import com.airline.config.CustomUserDetails;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt", "createdBy", "updatedBy" }, allowGetters = true)
public abstract class AuditLogs implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, updatable = false)
	private Date createdAt;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", nullable = false)
	private Date updatedAt;

	@Column(name = "created_uid", nullable = false, updatable = false)
	private String createdBy;

	@Column(name = "updated_uid")
	private String updatedBy;

	@PrePersist
	public void onSave() {

		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			this.createdBy = user.getUsername();
			this.updatedBy = user.getUsername();
		} else {
			this.createdBy = null;
		}

	}

	@PreUpdate
	public void onUpdate() {

		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			this.updatedBy = user.getUsername();

		} else {
			this.updatedBy = null;
		}

	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String toString() {
		return "UserAuditModel [createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", createdBy=" + createdBy
				+ ", updatedBy=" + updatedBy + "]";
	}

}