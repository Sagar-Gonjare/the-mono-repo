package or.dnyanyog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Patients {
  @Column @GeneratedValue @Id private Long patientId;
  @NotEmpty @Column private String patientNameEnglish;
  @NotEmpty @Column private String patientNameMarathi;
  @NotEmpty @Column private String mobileNumber;
  @NotEmpty @Column private String gender;
  @NotEmpty @Column private String birthDate;
  @NotEmpty @Column private String firstExaminationDate;
  @NotEmpty @Column private String address;
  @NotEmpty @Column private String patientStatus;

  public static Patients getInstance() {
    return new Patients();
  }

  public long getPatientId() {
    return patientId;
  }

  public Patients setPatientId(long patientId) {
    this.patientId = patientId;
    return this;
  }

  public String getPatientNameEnglish() {
    return patientNameEnglish;
  }

  public Patients setPatientNameEnglish(String patientNameEnglish) {
    this.patientNameEnglish = patientNameEnglish;
    return this;
  }

  public String getPatientNameMarathi() {
    return patientNameMarathi;
  }

  public Patients setPatientNameMarathi(String patientNameMarathi) {
    this.patientNameMarathi = patientNameMarathi;
    return this;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public Patients setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
    return this;
  }

  public String getGender() {
    return gender;
  }

  public Patients setGender(String gender) {
    this.gender = gender;
    return this;
  }

  public String getBirthDate() {
    return birthDate;
  }

  public Patients setBirthDate(String birthDate) {
    this.birthDate = birthDate;
    return this;
  }

  public String getFirstExaminationDate() {
    return firstExaminationDate;
  }

  public Patients setFirstExaminationDate(String firstExaminationDate) {
    this.firstExaminationDate = firstExaminationDate;
    return this;
  }

  public String getAdress() {
    return address;
  }

  public Patients setAdress(String adress) {
    this.address = adress;
    return this;
  }

  public String getPatientStatus() {
    return patientStatus;
  }

  public Patients setPatientStatus(String patientStatus) {
    this.patientStatus = patientStatus;
    return this;
  }
}
