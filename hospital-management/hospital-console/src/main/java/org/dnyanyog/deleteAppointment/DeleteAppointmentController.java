package org.dnyanyog.deleteAppointment;

import java.io.IOException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.dnyanyog.appointments.Appointments;
import org.dnyanyog.cases.Cases;
import org.dnyanyog.common.ApiEndPoints;
import org.dnyanyog.common.CommonScreen;
import org.dnyanyog.common.RestAPIClient;
import org.dnyanyog.dashboard.Dashboard;
import org.dnyanyog.dto.appointments.AppointmentData;
import org.dnyanyog.dto.appointments.AppointmentResponse;
import org.dnyanyog.patients.Patients;
import org.dnyanyog.users.Users;

public class DeleteAppointmentController extends CommonScreen {

  @FXML private Button patient;

  @FXML private Button cases;

  @FXML private Button appointments;

  @FXML private Button dashboard;

  @FXML private Button back;
  @FXML private Button delete;
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

  public void delete(ActionEvent event) {

    if (searchAppointmentId.getText().isEmpty()) {
      System.out.println("Please provide a Appointment ID.");
      return;
    }

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Inactive");
    alert.setHeaderText("Delete Appointment");
    alert.setContentText("Do you want to delete the Appointment?");
    ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
    ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
    alert.getButtonTypes().setAll(okButton, cancelButton);
    Optional<ButtonType> result = alert.showAndWait();

    if (result.isPresent() && result.get() == okButton) {
      AppointmentResponse response =
          apiClient.sendDeleteRequest(
              ApiEndPoints.DELETE_APPOINTMENT.getUrl() + searchAppointmentId.getText(),
              AppointmentResponse.class);

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
