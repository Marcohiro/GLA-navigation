package Vue;

import ModeleGPS.Historique;
import ModeleGPS.Utilisateur;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Scene1 {
	
	static Utilisateur user = new Utilisateur("offline", "offline", null, new Historique());

	public static Scene createScene(Stage stage, Utilisateur user) {
		
		// Menu Principal
		Label menuPrincipal = new Label("Menu principal");
		menuPrincipal.setLayoutX(350);
		Pane main = new Pane();
		// Interface entree du programme
		Button buttonTrajet = new Button("Trajet");
		buttonTrajet.setOnAction(e -> stage.setScene(Trajet.createScene(stage, user)));
		buttonTrajet.setLayoutX(50);
		buttonTrajet.setLayoutY(200);
		Button consulterHistorique = new Button("Consulter ou modifier historique");
		consulterHistorique.setLayoutX(500);
		consulterHistorique.setLayoutY(200);
		consulterHistorique.setOnAction(e -> stage.setScene(HistoriqueVue.createScene(stage, user)));
		main.getChildren().addAll(menuPrincipal, buttonTrajet, consulterHistorique);
		return new Scene(main, 800, 400);
	}
	
}
