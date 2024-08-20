package org.dnyanyog.updateCase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.apache.http.HttpStatus;
import org.dnyanyog.cases.Cases;
import org.dnyanyog.common.ApiEndPoints;
import org.dnyanyog.common.RequestMapper;
import org.dnyanyog.common.RestAPIClient;
import org.dnyanyog.dashboard.Dashboard;
import org.dnyanyog.dto.cases.CaseData;
import org.dnyanyog.dto.cases.CaseRequest;
import org.dnyanyog.dto.cases.CaseResponse;
import org.dnyanyog.patients.Patients;
import org.dnyanyog.users.Users;

public class UpdateCaseController {
  @FXML private Button patient;

  @FXML private Button cases;

  @FXML private Button appointments;

  @FXML private Button dashboard;

  @FXML private TextField patientName;

  @FXML private TextField patientId;

  @FXML private DatePicker examinationDate;

  @FXML private TextField caseNo;

  @FXML private TextField symptoms;

  @FXML private TextField prescription;

  @FXML private TextField caseId;

  @FXML private TextField searchPatientId;

  @FXML
  public void patient(ActionEvent event) {
    new Patients().show();
  }

  @FXML
  public void appointments(ActionEvent event) {
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
  @FXML private Button update;
  @FXML private Button search;

  @FXML
  public void cancel(ActionEvent event) {
    new Cases().show();
  }

  private RestAPIClient<CaseResponse> apiClient = new RestAPIClient<>();

  public void search(ActionEvent event) {

    String caseIdString = caseId.getText();
    if (caseIdString.isEmpty()) {
      showAlert("Error", "Please enter an appointment ID.");
      return;
    }
    try {
      CaseResponse response =
          apiClient.sendGetRequest(
              ApiEndPoints.SERACH_CASE_BY_CASEID.getUrl() + caseId.getText(), CaseResponse.class);

      if (response == null) {
        System.out.println("API response is null");
        return;
      }

      //   int statusCode = Integer.parseInt(response.getResponseCode());

      if (response.getResponseCode() >= 200 && response.getResponseCode() < 315) {
        System.out.println("Success");
        CaseData patientData = response.getData();

        if (patientData == null) {
          System.out.println("Patient data is null");
          return;
        }

        patientName.setText(patientData.getPatientName());
        patientId.setText(patientData.getPatientId());
        caseNo.setText(patientData.getCaseNo());
        symptoms.setText(patientData.getSymptoms());
        prescription.setText(patientData.getPrescription());

        String examinationDateStr = patientData.getExaminationDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (examinationDateStr != null && !examinationDateStr.isEmpty()) {
          LocalDate examinationLocalDate = LocalDate.parse(examinationDateStr, formatter);
          examinationDate.setValue(examinationLocalDate);
        } else {
          examinationDate.setValue(null);
        }

      } else {
        System.out.println("Error");
      }

    } catch (Exception e) {
      System.out.println("Exception occurred: " + e.getMessage());
      e.printStackTrace();
    }
  }

  public void update(ActionEvent event) {
    CaseRequest updateCaseRequest = new CaseRequest();
    updateCaseRequest.setCaseId(Long.parseLong(caseId.getText()));
    updateCaseRequest.setPatientName(patientName.getText());
    updateCaseRequest.setPatientId(patientId.getText());
    updateCaseRequest.setCaseNo(caseNo.getText());
    updateCaseRequest.setSymptoms(symptoms.getText());
    updateCaseRequest.setPrescription(prescription.getText());
    updateCaseRequest.setExaminationDate(examinationDate.getValue().toString());

    CaseResponse response =
        apiClient.sendPostRequest(
            ApiEndPoints.UPDATE_CASE.getUrl(),
            RequestMapper.convertToJsonString(updateCaseRequest),
            CaseResponse.class);

    System.out.print("*" + response);

    try {
      if (response.getResponseCode() >= 200 && response.getResponseCode() < 300) {
        System.out.println("Success");
        System.out.println(response.getData().getStatus());
        // successfulMessage.setVisible(true);
        showAlert("Update", "Case Updated successfully");
        new Cases().show();

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
