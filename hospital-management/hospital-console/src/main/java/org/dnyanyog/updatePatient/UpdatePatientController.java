package org.dnyanyog.updatePatient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.apache.http.HttpStatus;
import org.dnyanyog.cases.Cases;
import org.dnyanyog.common.ApiEndPoints;
import org.dnyanyog.common.RequestMapper;
import org.dnyanyog.common.RestAPIClient;
import org.dnyanyog.dto.patients.PatientData;
import org.dnyanyog.dto.patients.PatientRequest;
import org.dnyanyog.dto.patients.PatientResponse;
import org.dnyanyog.patients.Patients;
import org.dnyanyog.users.Users;

public class UpdatePatientController {

  @FXML private Button patient;

  @FXML private Button cases;

  @FXML private Button appointments;

  @FXML private Button dashboard;

  @FXML private Button search;

  @FXML private Button cancel;

  @FXML private Button update;

  @FXML private TextField patientId;

  @FXML private TextField patientNameEnglish;
  @FXML private TextField patientNameMarathi;
  @FXML private TextField mobileNumber;
  @FXML private ComboBox gender;
  @FXML private DatePicker birthDate;
  @FXML private DatePicker examinatiionDate;
  @FXML private TextField address;

  //  private RestAPIClient<PatientResponse> apiClient = new RestAPIClient<>();

  @FXML
  public void patient(ActionEvent event) {
    new Patients().show();
  }

  @FXML
  public void cases(ActionEvent event) {
    new Cases().show();
  }

  @FXML
  public void appointments(ActionEvent event) {
    new Cases().show();
  }

  @FXML
  public void users(ActionEvent event) {
    new Users().show();
  }

  @FXML
  public void dashboard(ActionEvent event) {
    //  new DashBoard().show();

  }

  private RestAPIClient<PatientResponse> apiClient = new RestAPIClient<>();

  @FXML
  public void search(ActionEvent event) {
    String patientIdString = patientId.getText();
    if (patientIdString.isEmpty()) {
      showAlert("Error", "Please enter an appointment ID.");
      return;
    }
    try {
      PatientResponse response =
          apiClient.sendGetRequest(
              ApiEndPoints.GET_PATIENT_BY_PATIENT_ID.getUrl() + patientId.getText(),
              PatientResponse.class);

      if (response == null) {
        System.out.println("API response is null");
        return;
      }

      //   int statusCode = Integer.parseInt(response.getResponseCode());

      if (response.getResponseCode() >= 200 && response.getResponseCode() < 315) {
        System.out.println("Success");
        PatientData patientData = response.getData();

        if (patientData == null) {
          System.out.println("Patient data is null");
          return;
        }

        patientNameEnglish.setText(patientData.getPatientNameEnglish());
        patientNameMarathi.setText(patientData.getPatientNameMarathi());
        mobileNumber.setText(patientData.getMobileNumber());
        gender.setValue(patientData.getGender());
        //     examinatiionDate.setVisible(patientData.getFirstExaminationDate());
        address.setText(patientData.getAdress());

        String examinationDateStr = patientData.getFirstExaminationDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (examinationDateStr != null && !examinationDateStr.isEmpty()) {
          LocalDate examinationDate = LocalDate.parse(examinationDateStr, formatter);
          examinatiionDate.setValue(examinationDate);
        } else {
          examinatiionDate.setValue(null);
        }

        String birthDateStr = patientData.getBirthDate();
        if (birthDateStr != null && !birthDateStr.isEmpty()) {
          LocalDate birthDateValue = LocalDate.parse(birthDateStr, formatter);
          birthDate.setValue(birthDateValue);
        } else {
          birthDate.setValue(null);
        }
      } else {
        System.out.println("Error");
      }

    } catch (Exception e) {
      System.out.println("Exception occurred: " + e.getMessage());
      e.printStackTrace();
    }
  }

  @FXML
  public void cancel(ActionEvent event) {
    new Patients().show();
  }

  @FXML
  public void update(ActionEvent event) {

    PatientRequest updatePatientRequest = new PatientRequest();
    updatePatientRequest.setPatientId(Long.parseLong(patientId.getText()));
    updatePatientRequest.setPatientNameEnglish(patientNameEnglish.getText());
    updatePatientRequest.setPatientNameMarathi(patientNameMarathi.getText());
    updatePatientRequest.setMobileNumber(mobileNumber.getText());
    updatePatientRequest.setBirthDate(birthDate.getValue().toString());
    updatePatientRequest.setAdress(address.getText());
    updatePatientRequest.setFirstExaminationDate(examinatiionDate.getValue().toString());
    updatePatientRequest.setGender(gender.getValue().toString());

    PatientResponse response =
        apiClient.sendPostRequest(
            ApiEndPoints.UPDATE_PATIENT.getUrl(),
            RequestMapper.convertToJsonString(updatePatientRequest),
            PatientResponse.class);

    System.out.print("*" + response);

    try {
      if (response.getResponseCode() >= 200 && response.getResponseCode() < 300) {
        System.out.println("Success");
        System.out.println(response.getData().getPatientStatus());
        // successfulMessage.setVisible(true);
        showAlert("Update", "Patient Update successfully");
        new Patients().show();

      } else if (response.getResponseCode() == HttpStatus.SC_CONFLICT) {
        // successfulMessage.setVisible(true);
      } else {
        System.out.println("Error");
        System.out.println(response.getResponseCode());
      }
    } catch (NumberFormatException e) {
      System.out.println("Invalid status code format: " + response.getResponseMsg());
    }
  }

  private void showAlert(String title, String message) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setContentText(message);
    alert.showAndWait();
  }
}
