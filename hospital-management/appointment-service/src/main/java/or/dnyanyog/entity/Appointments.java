package or.dnyanyog.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.*;

@Entity
@Component
@Table
public class Appointments {
  @Column @GeneratedValue @Id private Long appointmentId;

  @Column private String patientName;

  @Column private String patientId;

  @Column private String examinationDate;

  @Column private String appointmentTime;

  @Column private String status;

  public static Appointments getInstance() {
    return new Appointments();
  }

  public Long getAppointmentId() {
    return appointmentId;
  }

  public Appointments setAppointmentId(Long appointmentId) {
    this.appointmentId = appointmentId;
    return this;
  }

  public String getPatientName() {
    return patientName;
  }

  public Appointments setPatientName(String patientName) {
    this.patientName = patientName;
    return this;
  }

  public String getPatientId() {
    return patientId;
  }

  public Appointments setPatientId(String patientId) {
    this.patientId = patientId;
    return this;
  }

  public String getExaminationDate() {
    return examinationDate;
  }

  public Appointments setExaminationDate(String examinationDate) {
    this.examinationDate = examinationDate;
    return this;
  }

  public String getAppointmentTime() {
    return appointmentTime;
  }

  public Appointments setAppointmentTime(String appointmentTime) {
    this.appointmentTime = appointmentTime;
    return this;
  }

  public String getStatus() {
    return status;
  }

  public Appointments setStatus(String status) {
    this.status = status;
    return this;
  }
}
