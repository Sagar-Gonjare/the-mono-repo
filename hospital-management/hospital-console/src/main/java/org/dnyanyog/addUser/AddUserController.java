package org.dnyanyog.addUser;

import org.dnyanyog.appointments.Appointments;
import org.dnyanyog.cases.Cases;
import org.dnyanyog.common.ApiEndPoints;
import org.dnyanyog.common.RequestMapper;
import org.dnyanyog.common.RestAPIClient;
import org.dnyanyog.dashboard.Dashboard;
import org.dnyanyog.dto.appointments.AppointmentData;
import org.dnyanyog.dto.appointments.AppointmentRequest;
import org.dnyanyog.dto.appointments.AppointmentResponse;
import org.dnyanyog.dto.users.UserRequest;
import org.dnyanyog.dto.users.UserResponse;
import org.dnyanyog.patients.Patients;
import org.dnyanyog.users.Users;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class AddUserController {
  @FXML private Button patient;

  @FXML private Button cases;

  @FXML private Button appointments;

  @FXML private Button dashboard;

  @FXML private TextField userName;

  @FXML private TextField email;

  @FXML private TextField mobile;

  @FXML private ComboBox role;

  @FXML private PasswordField password;

  @FXML private PasswordField confirmPassword;

  private RestAPIClient<UserResponse> apiClient = new RestAPIClient<>();

  @FXML
  public void appointments(ActionEvent event) {
    new Appointments().show();
  }

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

  @FXML
  public void cancel(ActionEvent event) {
    new Users().show();
  }

  @FXML
  public void save(ActionEvent event) {

    if ((userName.getText().isEmpty()
        || email.getText().isEmpty()
        || role.getValue() == null
        || mobile.getText().isEmpty()
        || password.getText().isEmpty()
        || confirmPassword.getText().isEmpty())) {

      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Incomplete data");
      alert.setContentText("Fill all the fields");

      // Show the alert box
      alert.showAndWait();
    }
    if (password.getText().contentEquals(confirmPassword.getText())) {

      UserRequest request = new UserRequest();
      request.setUserName(userName.getText());
      request.seteMail(email.getText());
      request.setMobileNumber(mobile.getText());
      request.setRole(role.getValue().toString());
      request.setStatus("Active");
      request.setPassword(password.getText());
      System.out.println(request);

      UserResponse response =
          apiClient.sendPostRequest(
              ApiEndPoints.ADD_USER.getUrl(),
              RequestMapper.convertToJsonString(request),
              UserResponse.class);

      if (response.getResponseCode() >= 200 && response.getResponseCode() < 300) {
        System.out.println("*******Success********");
        System.out.println(response.getResponseMsg());
        System.out.println("User Id:- " + response.getData().getUserId());

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Cofirm");
        alert.setHeaderText("User Added !!!");
        alert.setContentText("User added successfully");

        // Show the alert box
        alert.showAndWait();

        new Users().show();

      } else {
        System.out.println("*******Error**********");
        System.out.println(response.getResponseMsg());
      }
    }
  }
}
