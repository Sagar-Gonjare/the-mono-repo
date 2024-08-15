package or.dnyanyog.dto.response;

import org.springframework.stereotype.Component;

@Component
public class PatientData {

  private long patientId;

  private String patientNameEnglish;

  private String patientNameMarathi;

  private String mobileNumber;

  private String gender;

  private String birthDate;

  private String firstExaminationDate;

  private String adress;

  private String patientStatus;

  public static PatientData getInstance() {
    return new PatientData();
  }

  public long getPatientId() {
    return patientId;
  }

  public PatientData setPatientId(long patientId) {
    this.patientId = patientId;
    return this;
  }

  public String getPatientNameEnglish() {
    return patientNameEnglish;
  }

  public PatientData setPatientNameEnglish(String patientNameEnglish) {
    this.patientNameEnglish = patientNameEnglish;
    return this;
  }

  public String getPatientNameMarathi() {
    return patientNameMarathi;
  }

  public PatientData setPatientNameMarathi(String patientNameMarathi) {
    this.patientNameMarathi = patientNameMarathi;
    return this;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public PatientData setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
    return this;
  }

  public String getGender() {
    return gender;
  }

  public PatientData setGender(String gender) {
    this.gender = gender;
    return this;
  }

  public String getBirthDate() {
    return birthDate;
  }

  public PatientData setBirthDate(String birthDate) {
    this.birthDate = birthDate;
    return this;
  }

  public String getFirstExaminationDate() {
    return firstExaminationDate;
  }

  public PatientData setFirstExaminationDate(String firstExaminationDate) {
    this.firstExaminationDate = firstExaminationDate;
    return this;
  }

  public String getAdress() {
    return adress;
  }

  public PatientData setAdress(String adress) {
    this.adress = adress;
    return this;
  }

  public String getPatientStatus() {
    return patientStatus;
  }

  public PatientData setPatientStatus(String patientStatus) {
    this.patientStatus = patientStatus;
    return this;
  }
}
