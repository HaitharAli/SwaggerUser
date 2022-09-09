package com.user.swagger.entity;

import java.util.Date;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdDate", "modifiedDate" }, allowGetters = true)
@Data
public abstract class AbstractEntity {

	private String createdBy;
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdDate;
	private String modifiedBy;
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date modifiedDate;
	@Version
	private Integer version;

}
