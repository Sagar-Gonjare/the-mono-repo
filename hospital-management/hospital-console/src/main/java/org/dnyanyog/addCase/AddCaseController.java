package org.dnyanyog.addCase;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.dnyanyog.cases.Cases;
import org.dnyanyog.common.ApiEndPoints;
import org.dnyanyog.common.RequestMapper;
import org.dnyanyog.common.RestAPIClient;
import org.dnyanyog.dashboard.Dashboard;
import org.dnyanyog.dto.cases.CaseRequest;
import org.dnyanyog.dto.cases.CaseResponse;
import org.dnyanyog.patients.Patients;
import org.dnyanyog.users.Users;

public class AddCaseController {
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
  @FXML private Button save;

  @FXML
  public void cancel(ActionEvent event) {
    new Cases().show();
  }

  private RestAPIClient<CaseResponse> apiClient = new RestAPIClient<>();

  @FXML
  public void save(ActionEvent event) {
    if ((patientName.getText().isEmpty()
        || patientId.getText().isEmpty()
        || caseNo.getText().isEmpty()
        || symptoms.getText().isEmpty()
        || examinationDate.getValue() == null
        || prescription.getText().isEmpty())) {

      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Incomplete data");
      alert.setContentText("Fill all the firlds");

      // Show the alert box
      alert.showAndWait();

    } else {

      CaseRequest request = new CaseRequest();
      request.setCaseNo(caseNo.getText());
      request.setExaminationDate(examinationDate.getValue().toString());
      request.setPatientId(patientId.getText());
      request.setPatientName(patientName.getText());
      request.setSymptoms(symptoms.getText());
      request.setPrescription(prescription.getText());
      request.setStatus("Active");
      System.out.println(request);

      CaseResponse response =
          apiClient.sendPostRequest(
              ApiEndPoints.ADD_CASE.getUrl(),
              RequestMapper.convertToJsonString(request),
              CaseResponse.class);

      if (response.getResponseCode() >= 200 && response.getResponseCode() < 300) {
        System.out.println("*******Success********");
        System.out.println(response.getResponseMsg());
        System.out.println("Patient Id:- " + response.getData().getPatientId());

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Cofirm");
        alert.setHeaderText("Case Added !!!");
        alert.setContentText("Case added successfully for patient Id :- " + patientId.getText());

        // Show the alert box
        alert.showAndWait();

      } else {
        System.out.println("*******Error**********");
        System.out.println(response.getResponseMsg());
        Alert alert = new Alert(AlertType.ERROR);

        alert.setTitle("Error");
        alert.setHeaderText("*******Error**********");
        alert.setContentText(response.getResponseMsg());

        // Show the alert box
        alert.showAndWait();
      }
    }
  }
}
