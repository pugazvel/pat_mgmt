package com.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.demo.common.Constants;

@Entity
@Table(name = "Patient", uniqueConstraints = @UniqueConstraint(columnNames = { "patient_id", "institution_id" }))
public class Patient extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull(message = Constants.PATIENT_ID_MANDATORY)
	@NotEmpty(message = Constants.PATIENT_ID_MANDATORY)
	@Column(name = "patient_id")
	private String patientId;

	@NotNull(message = Constants.PATIENT_NAME_MANDATORY)
	@NotEmpty(message = Constants.PATIENT_NAME_MANDATORY)
	private String name;

	@NotNull(message = Constants.INSTITUTION_ID_MANDATORY)
	@ManyToOne(targetEntity = Institution.class)
	@JoinColumn(name = "institution_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_patient_institution"))
	private Institution institution;

	private String dob;

	private String gender;

	private String description;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

}