package org.dnyanyog.addPatient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.dnyanyog.cases.Cases;
import org.dnyanyog.common.ApiEndPoints;
import org.dnyanyog.common.RequestMapper;
import org.dnyanyog.common.RestAPIClient;
import org.dnyanyog.dashboard.Dashboard;
import org.dnyanyog.dto.patients.PatientData;
import org.dnyanyog.dto.patients.PatientRequest;
import org.dnyanyog.dto.patients.PatientResponse;
import org.dnyanyog.patients.Patients;
import org.dnyanyog.users.Users;

public class AddPatientController {
  @FXML private Button patient;

  @FXML private Button cases;

  @FXML private Button appointments;

  @FXML private Button dashboard;

  @FXML
  public void patient(ActionEvent event) {
    new Patients().show();
  }

  @FXML
  public void cases(ActionEvent event) {
    new Cases().show();
  }

  @FXML
  public void users(ActionEvent event) {
    new Users().show();
  }

  @FXML
  public void dashboard(ActionEvent event) {
    new Dashboard().show();
  }

  @FXML private Button cancel;
  @FXML private Button save;

  @FXML private TextField patientNameEnglish;
  @FXML private TextField patientNameMarathi;
  @FXML private TextField mobileNumber;
  @FXML private ComboBox gender;
  @FXML private DatePicker birthDate;
  @FXML private DatePicker examinatiionDate;
  @FXML private TextField adresss;

  private RestAPIClient<PatientResponse> apiClient = new RestAPIClient<>();

  @FXML
  public void cancel(ActionEvent event) {
    new Patients().show();
  }

  @FXML
  public void save(ActionEvent event) {

    if ((patientNameEnglish.getText().isEmpty()
        || patientNameMarathi.getText().isEmpty()
        || mobileNumber.getText().isEmpty()
        || adresss.getText().isEmpty()
        || gender.getValue() == null
        || birthDate.getValue() == null
        || examinatiionDate.getValue() == null
        || gender.getValue() == null)) {

      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Incomplete data");
      alert.setContentText("Fill all the firlds");

      // Show the alert box
      alert.showAndWait();

    } else {

      PatientRequest request = new PatientRequest();
      request.setPatientNameEnglish(patientNameEnglish.getText());
      request.setPatientNameMarathi(patientNameMarathi.getText());
      request.setMobileNumber(mobileNumber.getText());
      request.setGender(gender.getValue().toString());
      request.setBirthDate(birthDate.getValue().toString());
      request.setFirstExaminationDate(examinatiionDate.getValue().toString());
      request.setAdress(adresss.getText());
      request.setPatientStatus("Active");
      System.out.println(request);

      PatientResponse response =
          apiClient.sendPostRequest(
              ApiEndPoints.ADD_PATIENT.getUrl(),
              RequestMapper.convertToJsonString(request),
              PatientResponse.class);

      if (response.getResponseCode() >= 200 && response.getResponseCode() < 300) {
        System.out.println("*******Success********");
        System.out.println(response.getResponseMsg());
        System.out.println("Patient Id:- " + response.getData().getPatientId());

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Cofirm");
        alert.setHeaderText("Patient Added !!!");
        alert.setContentText("Patient added successfully");

        // Show the alert box
        alert.showAndWait();

        PatientData t = new PatientData();

        t.setPatientNameEnglish(response.getData().getPatientNameEnglish());
        t.setPatientNameMarathi(response.getData().getPatientNameMarathi());
        t.setMobileNumber(response.getData().getMobileNumber());
        t.setGender(response.getData().getGender());
        t.setBirthDate(response.getData().getBirthDate());
        t.setFirstExaminationDate(response.getData().getFirstExaminationDate());
        t.setAdress(response.getData().getAdress());
        t.setPatientStatus("Active");

      } else {
        System.out.println("*******Error**********");
        System.out.println(response.getResponseMsg());
      }
    }
  }
}
