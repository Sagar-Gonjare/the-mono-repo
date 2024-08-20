package org.dnyanyog.deletePatient;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import okhttp3.OkHttpClient;
import org.dnyanyog.cases.Cases;
import org.dnyanyog.common.ApiEndPoints;
import org.dnyanyog.common.RestAPIClient;
import org.dnyanyog.dto.patients.PatientData;
import org.dnyanyog.dto.patients.PatientResponse;
import org.dnyanyog.patients.Patients;
import org.dnyanyog.users.Users;

public class DeletePatientController {

  @FXML private Button patient;

  @FXML private Button cases;

  @FXML private Button appointments;

  @FXML private Button dashboard;

  @FXML private Button search;

  @FXML private Button cancel;

  @FXML private Button delete;

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

  @FXML private Label resultLabel;
  private final OkHttpClient httpClient = new OkHttpClient();
  private final Gson gson = new Gson();
  private RestAPIClient<PatientResponse> apiClient = new RestAPIClient<>();

  @FXML
  public void search(ActionEvent event) {
    //	    String patientIdString = patientId.getText();
    //	    if (patientIdString.isEmpty()) {
    //	      showAlert("Error", "Please enter an appointment ID.");
    //	      return;
    //	    }
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
  public void delete(ActionEvent event) {

    if (patientId.getText().isEmpty()) {
      System.out.println("Please provide a patient ID.");
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
      PatientResponse response =
          apiClient.sendDeleteRequest(
              ApiEndPoints.DELETEPATIENT.getUrl() + patientId.getText(), PatientResponse.class);

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
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle(title);
    alert.setContentText(message);
    alert.showAndWait();
  }

  private void populateAppointmentDetails(JsonObject patient) {
    patientId.setText(patient.get("patientId").getAsString());
    patientNameEnglish.setText(patient.get("patientNameEnglish").getAsString());
    patientNameMarathi.setText(patient.get("patientNameMarathi").getAsString());
    mobileNumber.setText(patient.get("mobileNumber").getAsString());
    address.setText(patient.get("adresss").getAsString());
    gender.setPromptText(patient.get("gender").getAsString());
    //  patientNameEnglish.setText(patient.get("patientName").getAsString());
    examinatiionDate.setValue(LocalDate.parse(patient.get("examinationDate").getAsString()));
  }

  private void clearAppointmentDetails() {
    patientId.clear();
    patientNameEnglish.clear();
    patientNameMarathi.clear();
    mobileNumber.clear();
    address.clear();
    //  gender.clearDirty();
    patientNameEnglish.clear();
  }
}
