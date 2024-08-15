package org.dnyanyog.cases;

import org.dnyanyog.addCase.AddCase;
import org.dnyanyog.addPatient.AddPatient;
import org.dnyanyog.appointments.Appointments;
import org.dnyanyog.deleteCase.DeleteCase;
import org.dnyanyog.deletePatient.DeletePatient;
import org.dnyanyog.patients.Patients;
import org.dnyanyog.updateCase.UpdateCase;
import org.dnyanyog.updatePatient.UpdatePatient;
import org.dnyanyog.users.Users;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CasesController {
	
	  @FXML private Button patient;

	  @FXML private Button cases;

	  @FXML private Button appointments;

	  @FXML private Button users;

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
		  new AddCase().show();

	  }
	  @FXML
	  public void edit(ActionEvent event) {
		  new UpdateCase().show();

	  }
	  @FXML
	  public void delete(ActionEvent event) {
		  new DeleteCase().show();

	  }
	 
}
