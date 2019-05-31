package Vue;

import ModeleGPS.Historique;
import ModeleGPS.Utilisateur;
import ModeleGPS.Ville;
import ModeleGPS.Parametres;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ParametresVue {

	static Utilisateur user = new Utilisateur("offline", "offline", null, new Historique());
	static Ville villeArr, villeDep;
	static Parametres p;

	public static Scene createScene(Stage stage, Utilisateur user2, Ville villeDep, Ville villeArr) {
		// Menu parametres
		Pane paramPane = new Pane();
		ComboBox<String> choix = new ComboBox<String>();
		Label prixSaisir = new Label("Saisir le prix maximal");
		prixSaisir.setLayoutX(100);
		prixSaisir.setLayoutY(100);
		TextField prixMaximal = new TextField();
		prixMaximal.setLayoutX(300);
		prixMaximal.setLayoutY(100);
		Label co2Saisir = new Label("Sasir le volume de CO2 maximal");
		co2Saisir.setLayoutX(100);
		co2Saisir.setLayoutY(200);
		TextField volumeSaisir = new TextField();
		volumeSaisir.setLayoutX(300);
		volumeSaisir.setLayoutY(200);
		Label choixRouC = new Label("Choisir entre plus court ou plus long");
		choixRouC.setLayoutX(100);
		choixRouC.setLayoutY(300);
		choix.getItems().addAll("Temps plus court", "Distance plus courte");
		choix.setLayoutX(300);
		choix.setLayoutY(300);
		Button choixV = new Button("suivant");
		Button retour7 = new Button("retour");
		choixV.setLayoutX(200);
		choixV.setLayoutY(350);
		retour7.setLayoutX(500);
		retour7.setLayoutY(350);
		retour7.setOnAction(c -> {
			stage.setScene(Trajet.createScene(stage, user));
		});
		choixV.setOnAction(c ->{
			if(prixMaximal.getText().equals("")) {
				manquePrix();
			} else if(volumeSaisir.getText().equals("")) {
				manqueVolume();
			} else if(choix.getValue() == null) {
				manqueChoix();
			} else {
				//On force l'appel
				if(choix.getValue().equals("Temps plus court")) {
				p = new Parametres(Integer.parseInt(prixMaximal.getText()), Integer.parseInt(volumeSaisir.getText()), true, false, true);
				} else {
					p = new Parametres(Integer.parseInt(prixMaximal.getText()), Integer.parseInt(volumeSaisir.getText()), true, true, false);
				}
				user.getHistorique().ajouterParametres(p);
				stage.setScene(Vehicules.createScene(stage, user, villeDep, villeArr, p));
			}
		});
		paramPane.getChildren().addAll(prixSaisir, prixMaximal, co2Saisir, volumeSaisir,choixRouC, choix, choixV, retour7);
		 return new Scene(paramPane, 800, 400);
	}
	
	// Alerte : cas : Le prix max n'est pas renseigné
	private static void manquePrix() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Le trajet n'a pas été lancé");
		alert.setHeaderText("Le trajet a échouée : ");
		alert.setContentText("Prix maximal manquant");
		alert.showAndWait();
	}
	
	// Alerte : cas : Le volume n'est pas renseigné
	private static void manqueVolume() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Le trajet n'a pas été lancé");
		alert.setHeaderText("Le trajet a échouée : ");
		alert.setContentText("Volume de CO2 manquant");
		alert.showAndWait();
	}
	
	// Alerte : cas : le choix n'est pas renseigné
	private static void manqueChoix() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Le trajet n'a pas été lancé");
		alert.setHeaderText("Le trajet a échouée : ");
		alert.setContentText("Choix non effectué");
		alert.showAndWait();
	}
	
}
