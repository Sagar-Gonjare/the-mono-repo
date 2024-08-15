package org.dnyanyog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table
public class Cases {
  @Column @Id @GeneratedValue private Long caseId;

  @NotNull @Column private String patientName;
  @NotNull @Column private String patientId;
  @NotNull @Column private String caseNumber;
  @NotNull @Column private String examinationDate;
  @NotNull @Column private String symptoms;
  @NotNull @Column private String prescription;
  @NotNull @Column private String status;

  public static Cases getInsatnce() {
    return new Cases();
  }

  public Long getCaseId() {
    return caseId;
  }

  public Cases setCaseId(Long caseId) {
    this.caseId = caseId;
    return this;
  }

  public String getPatientName() {
    return patientName;
  }

  public Cases setPatientName(String patientName) {
    this.patientName = patientName;
    return this;
  }

  public String getPatientId() {
    return patientId;
  }

  public Cases setPatientId(String patientId) {
    this.patientId = patientId;
    return this;
  }

  public String getCaseNo() {
    return caseNumber;
  }

  public Cases setCaseNumber(String caseNo) {
    this.caseNumber = caseNo;
    return this;
  }

  public String getExaminationDate() {
    return examinationDate;
  }

  public Cases setExaminationDate(String examinationDate) {
    this.examinationDate = examinationDate;
    return this;
  }

  public String getSymptoms() {
    return symptoms;
  }

  public Cases setSymptoms(String symptoms) {
    this.symptoms = symptoms;
    return this;
  }

  public String getPrescription() {
    return prescription;
  }

  public Cases setPrescription(String prescription) {
    this.prescription = prescription;
    return this;
  }

  public String getStatus() {
    return status;
  }

  public Cases setStatus(String status) {
    this.status = status;
    return this;
  }
}
