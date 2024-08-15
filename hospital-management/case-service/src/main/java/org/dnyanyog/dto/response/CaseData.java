package org.dnyanyog.dto.response;

import org.springframework.stereotype.Component;

@Component
public class CaseData {
  private Long caseId;

  private String patientName;

  private String patientId;

  private String caseNo;

  private String examinationDate;

  private String symptoms;

  private String prescription;

  private String status;

  public static CaseData getInstance() {
    return new CaseData();
  }

  public Long getCaseId() {
    return caseId;
  }

  public CaseData setCaseId(Long caseId) {
    this.caseId = caseId;
    return this;
  }

  public String getPatientName() {
    return patientName;
  }

  public CaseData setPatientName(String patientName) {
    this.patientName = patientName;
    return this;
  }

  public String getPatientId() {
    return patientId;
  }

  public CaseData setPatientId(String patientId) {
    this.patientId = patientId;
    return this;
  }

  public String getCaseNo() {
    return caseNo;
  }

  public CaseData setCaseNo(String caseNo) {
    this.caseNo = caseNo;
    return this;
  }

  public String getExaminationDate() {
    return examinationDate;
  }

  public CaseData setExaminationDate(String examinationDate) {
    this.examinationDate = examinationDate;

    return this;
  }

  public String getSymptoms() {
    return symptoms;
  }

  public CaseData setSymptoms(String symptoms) {
    this.symptoms = symptoms;
    return this;
  }

  public String getPrescription() {
    return prescription;
  }

  public CaseData setPrescription(String prescription) {
    this.prescription = prescription;
    return this;
  }

  public String getStatus() {
    return status;
  }

  public CaseData setStatus(String status) {
    this.status = status;
    return this;
  }
}
