package com.sekolahbackend.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Data
@MappedSuperclass
public class Persistence {

	private static final long serialVersionUID = 1L;
//	private static final long serialVersionUID = -3268940466026097783L;

	public enum Status {
		ACTIVE, NOT_ACTIVE
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;

	@Column(length = 50)
	@CreatedBy
	private String createdBy;

	@Column(length = 50)
	@LastModifiedBy
	private String updatedBy;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdTime;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedTime;

	@Column(length = 50)
	@Enumerated(EnumType.STRING)
	private Status status;


	@PrePersist
	public void prePersist() {

		setCreatedTime(new Date());
		setUpdatedTime(new Date());
		setStatus(Status.ACTIVE);
		setCreatedBy("system");
	}

	@PreUpdate
	public void preUpdate() {

		setUpdatedTime(new Date());
		setUpdatedBy("system");
	}
	
}