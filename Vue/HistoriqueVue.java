package Vue;

import ModeleGPS.Historique;
import ModeleGPS.Utilisateur;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HistoriqueVue {
	
	static Utilisateur user = new Utilisateur("offline", "offline", null, new Historique());
	
	public static Scene createScene(Stage stage, Utilisateur user) {
				
		// Menu ConsulterHistorique
		Pane menuHistorique = new Pane();
		Label historiqueL = new Label("historique");
		historiqueL.setLayoutX(350);
		Button listeVehicules = new Button("vehicules");
		listeVehicules.setLayoutX(350);
		listeVehicules.setLayoutY(50);
		Button listeParametres = new Button("parametres");
		listeParametres.setLayoutX(350);
		listeParametres.setLayoutY(150);
		Button listeTrajets = new Button("Trajets");
		listeTrajets.setLayoutX(350);
		listeTrajets.setLayoutY(250);
		Button retour = new Button("retour");
		retour.setLayoutX(350);
		retour.setLayoutY(350);
		listeVehicules.setOnAction(b -> {
			stage.setScene(HistoriqueVehicules.createScene(stage, user));
		});
		listeParametres.setOnAction(b -> {
			stage.setScene(HistoriqueParametres.createScene(stage, user));
		});
		listeTrajets.setOnAction(b -> {
			stage.setScene(HistoriqueTrajets.createScene(stage, user));
		});
		retour.setOnAction(b ->{
			stage.setScene(Scene1.createScene(stage, user));
		});
		menuHistorique.getChildren().addAll(listeVehicules, listeParametres, listeTrajets, retour);
		return new Scene(menuHistorique, 800, 400);
		
	}
	
}
