package org.dnyanyog.addAppointment;


import org.dnyanyog.appointments.Appointments;
import org.dnyanyog.cases.Cases;
import org.dnyanyog.common.ApiEndPoints;
import org.dnyanyog.common.RequestMapper;
import org.dnyanyog.common.RestAPIClient;
import org.dnyanyog.dashboard.Dashboard;
import org.dnyanyog.dto.appointments.AppointmentData;
import org.dnyanyog.dto.appointments.AppointmentRequest;
import org.dnyanyog.dto.appointments.AppointmentResponse;
import org.dnyanyog.patients.Patients;
import org.dnyanyog.users.Users;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AddAppointmentController {

  @FXML private Button patient;

  @FXML private Button cases;

  @FXML private Button appointments;

  @FXML private Button dashboard;

  @FXML private TextField pateintName;

  @FXML private TextField patientId;

  @FXML private DatePicker examinationDate;

  @FXML private ComboBox<String> hour;
  
  @FXML private ComboBox<String> minute;
  
  @FXML private ComboBox<String> timePeriod;

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
    new Appointments().show();
  }

	private RestAPIClient<AppointmentResponse> apiClient = new RestAPIClient<>();

  
  @FXML
  public void save(ActionEvent event) {
	  
	  if ((pateintName.getText().isEmpty()||patientId.getText().isEmpty()
			  ||hour.getValue() == null || minute.getValue() == null
				|| timePeriod.getValue() == null|| examinationDate.getValue()==null)) {
		  
		  Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText("Incomplete data");
	        alert.setContentText("Fill all the firlds");

	        // Show the alert box
	        alert.showAndWait();
		  
	  }else {
	  
	  AppointmentRequest request= new AppointmentRequest();
	  request.setPatientId(patientId.getText());
	  request.setPatientName(pateintName.getText());
	  request.setExaminationDate(examinationDate.getValue().toString());
	  request.setStatus("Pending");
	  request.setAppointmentTime(hour.getValue() + ":" + minute.getValue() + " " + timePeriod.getValue());
	  System.out.println(request);
	  
	  AppointmentResponse response = apiClient.sendPostRequest(ApiEndPoints.ADD_APPOINTMENT.getUrl(),
				RequestMapper.convertToJsonString(request), AppointmentResponse.class);
	  
	  if (response.getResponseCode() >= 200 && response.getResponseCode() < 300) {
			System.out.println("*******Success********");
			System.out.println(response.getResponseMsg());
			System.out.println("Appointment Id:- " + response.getData().getAppointmentId());
	  
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
	        alert.setTitle("Cofirm");
	        alert.setHeaderText("Appoitnment Added !!!");
	        alert.setContentText("Appointment added successfully");

	        // Show the alert box
	        alert.showAndWait();
	  
	  AppointmentData t = new AppointmentData();
	//	t.setAppointmentId(String.valueOf(response.getData().getAppointmentId()));
		t.setPatientId(response.getData().getPatientId());
		t.setPatientName(response.getData().getPatientName());
		t.setExaminationDate(response.getData().getExaminationDate());
		t.setAppointmentTime(response.getData().getAppointmentTime());
		t.setStatus(response.getData().getStatus());
		
		new Appointments().show();

	  }else {
			System.out.println("*******Error**********");
			System.out.println(response.getResponseMsg());
		}
	  }
  }
}
