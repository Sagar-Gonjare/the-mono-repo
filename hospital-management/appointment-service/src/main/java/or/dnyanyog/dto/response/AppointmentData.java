package or.dnyanyog.dto.response;

import org.springframework.stereotype.Component;

@Component
public class AppointmentData {
  private Long appointmentId;

  private String patientName;

  private String patientId;

  private String examinationDate;

  private String appointmentTime;

  private String status;

  public static AppointmentData getInstance() {
    return new AppointmentData();
  }

  public Long getAppointmentId() {
    return appointmentId;
  }

  public AppointmentData setAppointmentId(Long appointmentId) {
    this.appointmentId = appointmentId;
    return this;
  }

  public String getPatientName() {
    return patientName;
  }

  public AppointmentData setPatientName(String patientName) {
    this.patientName = patientName;
    return this;
  }

  public String getExaminationDate() {
    return examinationDate;
  }

  public AppointmentData setExaminationDate(String examinationDate) {
    this.examinationDate = examinationDate;
    return this;
  }

  public String getAppointmentTime() {
    return appointmentTime;
  }

  public AppointmentData setAppointmentTime(String appointmentTime) {
    this.appointmentTime = appointmentTime;
    return this;
  }

  public String getStatus() {
    return status;
  }

  public AppointmentData setStatus(String status) {
    this.status = status;
    return this;
  }

  public String getPatientId() {
    return patientId;
  }

  public AppointmentData setPatientId(String patientId) {
    this.patientId = patientId;
    return this;
  }
}
