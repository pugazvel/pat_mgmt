package com.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.demo.common.Constants;

@Entity
@Table(name = "examination")
public class Examination extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull(message = Constants.PATIENT_ID_MANDATORY)
	@Column(name = "patient_id")
	private long patientId;

	@NotNull(message = Constants.EXAM_NAME_MANDATORY)
	@NotEmpty(message = Constants.EXAM_NAME_MANDATORY)
	@Column(name = "exam_name")
	private String examName;

	@NotNull(message = Constants.EXAM_DATE_MANDATORY)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "exam_date")
	private Date examDate;

	private String description;

	private Double height;

	private Double weight;
	
	private int status;

	public long getId() {
		return id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

}