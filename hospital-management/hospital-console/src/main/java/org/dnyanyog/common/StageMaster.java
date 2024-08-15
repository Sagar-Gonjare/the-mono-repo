package org.dnyanyog.common;

import javafx.stage.Stage;

public class StageMaster {
	private static Stage stage;
	public static Stage getStage() {
		return stage;
		
	}
	public static void setStage(Stage stage) {
		StageMaster.stage=stage;
	}

}