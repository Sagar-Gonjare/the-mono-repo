package org.dnyanyog.dto.appointments;

public class AppointmentData {
  private String appointmentId;

  private String patientName;

  private String patientId;

  private String examinationDate;

  private String appointmentTime;

  private String status;

  public static String[] appointmentData = new String[6];

  public String getPatientName() {
    return patientName;
  }

  public void setPatientName(String patientName) {
	  appointmentData[1] = appointmentId;
    this.patientName = patientName;
  }

  public String getPatientId() {
    return patientId;
  }

  public void setPatientId(String patientId) {
	  appointmentData[2] = appointmentId;
    appointmentData[0] = appointmentId;

    this.patientId = patientId;
  }

  public String getExaminationDate() {
    return examinationDate;
  }

  public void setExaminationDate(String examinationDate) {
	  appointmentData[3] = appointmentId;
	  this.examinationDate = examinationDate;
  }

  public String getAppointmentTime() {
    return appointmentTime;
  }

  public void setAppointmentTime(String appointmentTime) {
	  appointmentData[4] = appointmentId;
	  this.appointmentTime = appointmentTime;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
	  appointmentData[5] = appointmentId;
	  this.status = status;
  }

  public String getAppointmentId() {
    return appointmentId;
  }

  public void setAppointmentId(String appointmentId) {
	  appointmentData[0] = appointmentId;
	  this.appointmentId = appointmentId;
  }
}
