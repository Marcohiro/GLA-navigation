package Vue;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setResizable(false);
		primaryStage.setScene(Login.create(primaryStage));
		primaryStage.show();
	}

	public static void main(String args[]) {
		launch(args);
	}

}