package org.dnyanyog.appointments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.dnyanyog.addAppointment.AddAppointment;
import org.dnyanyog.cases.Cases;
import org.dnyanyog.deleteAppointment.DeleteAppointment;
import org.dnyanyog.patients.Patients;
import org.dnyanyog.updateAppointment.UpdateAppointment;
import org.dnyanyog.users.Users;

public class AppointmentsController {

  @FXML private Button patient;

  @FXML private Button cases;

  @FXML private Button appointments;

  @FXML private Button dashboard;

  @FXML private Button add;

  @FXML private Button edit;

  @FXML private Button search;

  @FXML private Button delete;

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
    //  new DashBoard().show();

  }

  @FXML
  public void add(ActionEvent event) {
    new AddAppointment().show();
  }

  @FXML
  public void edit(ActionEvent event) {
    new UpdateAppointment().show();
  }

  @FXML
  public void delete(ActionEvent event) {
    new DeleteAppointment().show();
  }

  @FXML
  public void search(ActionEvent event) {
    // new Cases().show();

  }
}
