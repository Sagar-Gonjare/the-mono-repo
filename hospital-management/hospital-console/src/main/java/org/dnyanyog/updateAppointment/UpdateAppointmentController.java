package org.dnyanyog.updateAppointment;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.http.HttpStatus;
import org.dnyanyog.appointments.Appointments;
import org.dnyanyog.cases.Cases;
import org.dnyanyog.common.ApiEndPoints;
import org.dnyanyog.common.RequestMapper;
import org.dnyanyog.common.RestAPIClient;
import org.dnyanyog.dashboard.Dashboard;
import org.dnyanyog.dto.appointments.AppointmentData;
import org.dnyanyog.dto.appointments.AppointmentRequest;
import org.dnyanyog.dto.appointments.AppointmentResponse;
import org.dnyanyog.dto.patients.PatientRequest;
import org.dnyanyog.dto.patients.PatientResponse;
import org.dnyanyog.patients.Patients;
import org.dnyanyog.users.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UpdateAppointmentController {

  @FXML private Button patient;

  @FXML private Button cases;

  @FXML private Button appointments;

  @FXML private Button dashboard;

  @FXML private Button back;
  @FXML private Button update;
  @FXML private Button search;

  @FXML private TextField searchAppointmentId;
  @FXML private TextField searchPatientId;
  @FXML private TextField appointmentId;
  @FXML private TextField patientName;
  @FXML private TextField patientId;
  @FXML private DatePicker examinationDate;

  @FXML private TextField appointmentTime;

  @FXML private Label resultLabel;

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

  @FXML
  public void back(ActionEvent event) {
    new Appointments().show();
  }

  private RestAPIClient<AppointmentResponse> apiClient = new RestAPIClient<>();

  @FXML
  public void search(ActionEvent event) throws IOException {
    String appointmentIds = searchAppointmentId.getText();
    if (appointmentIds.isEmpty()) {
      showAlert("Error", "Please enter an appointment ID.");
      return;
    }
    try {
      AppointmentResponse response =
          apiClient.sendGetRequest(
              ApiEndPoints.GET_APPOINTMENT_BY_APPOINTENT_ID.getUrl()
                  + searchAppointmentId.getText(),
              AppointmentResponse.class);

      if (response == null) {
        System.out.println("API response is null");
        return;
      }

      //   int statusCode = Integer.parseInt(response.getResponseCode());

      if (response.getResponseCode() >= 200 && response.getResponseCode() < 315) {
        System.out.println("Success");
        AppointmentData data = response.getData();

        if (data == null) {
          System.out.println("Patient data is null");
          return;
        }
        appointmentId.setText(searchAppointmentId.getText().toString());
        patientName.setText(data.getPatientName());
        patientId.setText(data.getPatientId());
        appointmentTime.setText(data.getAppointmentTime());

        String examinationDateStr = data.getExaminationDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (examinationDateStr != null && !examinationDateStr.isEmpty()) {
          LocalDate examinationDateLocal = LocalDate.parse(examinationDateStr, formatter);
          examinationDate.setValue(examinationDateLocal);
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

    AppointmentRequest updateAppointmentRequest = new AppointmentRequest();
    updateAppointmentRequest.setAppointmentId(Long.parseLong(searchAppointmentId.getText()));
    appointmentId.setText(searchAppointmentId.getText());
    updateAppointmentRequest.setPatientName(patientName.getText());
    updateAppointmentRequest.setPatientId(patientId.getText());
    updateAppointmentRequest.setAppointmentTime(appointmentTime.getText());
    updateAppointmentRequest.setExaminationDate(examinationDate.getValue().toString());
   
    AppointmentResponse response =
        apiClient.sendPostRequest(
            ApiEndPoints.UPDATE_APPOINTMENTS.getUrl(),
            RequestMapper.convertToJsonString(updateAppointmentRequest),
            AppointmentResponse.class);

    System.out.print("*" + response);

    try {
      if (response.getResponseCode() >= 200 && response.getResponseCode() < 300) {
        System.out.println("Success");
        System.out.println(response.getData().getStatus());
        // successfulMessage.setVisible(true);
        showAlert("Update", "Patient Update successfully");
        new Appointments().show();

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
