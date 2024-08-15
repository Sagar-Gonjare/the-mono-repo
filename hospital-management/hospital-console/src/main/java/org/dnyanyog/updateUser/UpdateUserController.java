package org.dnyanyog.updateUser;

import org.apache.http.HttpStatus;
import org.dnyanyog.appointments.Appointments;
import org.dnyanyog.cases.Cases;
import org.dnyanyog.common.ApiEndPoints;
import org.dnyanyog.common.RequestMapper;
import org.dnyanyog.common.RestAPIClient;
import org.dnyanyog.dashboard.Dashboard;

import org.dnyanyog.dto.users.UserData;
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

public class UpdateUserController {

  @FXML private Button patient;

  @FXML private Button cases;

  @FXML private Button appointments;

  @FXML private Button dashboard;

  @FXML private TextField userName;

  @FXML private TextField email;

  @FXML private TextField mobile;

  @FXML private ComboBox role;

  @FXML private PasswordField password;

  @FXML private TextField userId;
  @FXML private Button search;

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

  @FXML private Button back;
  @FXML private Button update;
  private RestAPIClient<UserResponse> apiClient = new RestAPIClient<>();

  @FXML
  public void back(ActionEvent event) {
    new Users ().show();
  }

  

  @FXML
  public void search(ActionEvent event) {

    String patientIdString = userId.getText();
    if (patientIdString.isEmpty()) {
      showAlert("Error", "Please enter an appointment ID.");
      return;
    }
    try {
      UserResponse response =
          apiClient.sendGetRequest(
              ApiEndPoints.GET_USER_BY_USER_ID.getUrl() + userId.getText(),
              UserResponse.class);

      if (response == null) {
        System.out.println("API response is null");
        return;
      }

      //   int statusCode = Integer.parseInt(response.getResponseCode());

      if (response.getResponseCode() >= 200 && response.getResponseCode() < 315) {
        System.out.println("Success");
       UserData userData = response.getData();

        if (userData == null) {
          System.out.println("User data is null");
          return;
        }

        userName.setText(userData.getUserName());
        email.setText(userData.geteMail());
        mobile.setText(userData.getMobileNumber());
        role.setValue(userData.getRole());
        //     examinatiionDate.setVisible(patientData.getFirstExaminationDate());
      }
      else {
        System.out.println("Error");
      }

    } catch (Exception e) {
      System.out.println("Exception occurred: " + e.getMessage());
      e.printStackTrace();
    }
  }

  
  @FXML
  public void update(ActionEvent event) {

    UserRequest updateUserRequest = new UserRequest();
    updateUserRequest.setUserId(Long.parseLong(userId.getText()));
    updateUserRequest.setUserName(userName.getText());
    updateUserRequest.seteMail(email.getText());
    updateUserRequest.setMobileNumber(mobile.getText());
    updateUserRequest.setRole(role.getValue().toString());
    updateUserRequest.setPassword(password.getText());
    UserResponse response =
        apiClient.sendPostRequest(
            ApiEndPoints.UPDATE_USER.getUrl(),
            RequestMapper.convertToJsonString(updateUserRequest),
            UserResponse.class);

    System.out.print("*" + response);

    try {
      if (response.getResponseCode() >= 200 && response.getResponseCode() < 300) {
        System.out.println("Success");
        System.out.println(response.getData().getStatus());
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
