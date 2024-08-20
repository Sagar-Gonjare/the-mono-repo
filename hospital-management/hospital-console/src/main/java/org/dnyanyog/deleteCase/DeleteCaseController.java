package org.dnyanyog.deleteCase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.dnyanyog.cases.Cases;
import org.dnyanyog.common.ApiEndPoints;
import org.dnyanyog.common.RestAPIClient;
import org.dnyanyog.dashboard.Dashboard;
import org.dnyanyog.dto.cases.CaseData;
import org.dnyanyog.dto.cases.CaseResponse;
import org.dnyanyog.patients.Patients;
import org.dnyanyog.users.Users;

public class DeleteCaseController {

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
  @FXML private Button delete;
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

  public void delete(ActionEvent event) {

    if (caseId.getText().isEmpty()) {
      System.out.println("Please provide a case ID.");
      return;
    }

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Inactive");
    alert.setHeaderText("Delete Patient");
    alert.setContentText("Do you want to delete the patient?");
    ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
    ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
    alert.getButtonTypes().setAll(okButton, cancelButton);
    Optional<ButtonType> result = alert.showAndWait();

    if (result.isPresent() && result.get() == okButton) {
      CaseResponse response =
          apiClient.sendDeleteRequest(
              ApiEndPoints.DELETE_CASE.getUrl() + patientId.getText(), CaseResponse.class);

      if (response.getResponseCode() >= 200 && response.getResponseCode() < 300) {
        System.out.println("*Success*");
        // successfulMessage.setVisible(true);
        showAlert("Update", "Patient Deleted successfully");
        new Patients().show();

      } else {
        System.out.println("*Error*");
      }
    }
  }

  private void showAlert(String title, String message) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setContentText(message);
    alert.showAndWait();
  }
}
