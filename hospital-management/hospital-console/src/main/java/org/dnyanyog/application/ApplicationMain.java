package org.dnyanyog.application;




import org.dnyanyog.addAppointment.AddAppointment;
import org.dnyanyog.addUser.AddUser;
import org.dnyanyog.appointments.Appointments;
import org.dnyanyog.cases.Cases;
import org.dnyanyog.common.StageMaster;
import org.dnyanyog.patients.Patients;
import org.dnyanyog.updateAppointment.UpdateAppointment;
import org.dnyanyog.updatePatient.UpdatePatient;
import org.dnyanyog.users.Users;

import javafx.application.Application;
import javafx.stage.Stage;

public class ApplicationMain extends Application {

	public static void main(String args[]) {
		launch(args);

	}

	public void start(Stage primaryStage) {
		StageMaster.setStage(primaryStage);
		new Appointments().show();
	}
}