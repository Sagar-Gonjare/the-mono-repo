package org.dnyanyog.application;

import javafx.application.Application;
import javafx.stage.Stage;
import org.dnyanyog.appointments.Appointments;
import org.dnyanyog.common.StageMaster;

public class ApplicationMain extends Application {

  public static void main(String args[]) {
    launch(args);
  }

  public void start(Stage primaryStage) {
    StageMaster.setStage(primaryStage);
    new Appointments().show();
  }
}
