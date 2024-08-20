package org.dnyanyog.patients;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.dnyanyog.addPatient.AddPatient;
import org.dnyanyog.appointments.Appointments;
import org.dnyanyog.cases.Cases;
import org.dnyanyog.deletePatient.DeletePatient;
import org.dnyanyog.updatePatient.UpdatePatient;
import org.dnyanyog.users.Users;

public class PatientsController {
  @FXML private Button patient;

  @FXML private Button cases;

  @FXML private Button appointments;

  @FXML private Button dashboard;

  @FXML private Button add;

  @FXML private Button edit;

  @FXML private Button delete;

  @FXML
  public void patient(ActionEvent event) {
    new Patients().show();
  }

  public void appointments(ActionEvent event) {
    new Appointments().show();
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
    new AddPatient().show();
  }

  @FXML
  public void edit(ActionEvent event) {
    new UpdatePatient().show();
  }

  @FXML
  public void delete(ActionEvent event) {
    new DeletePatient().show();
  }
}
