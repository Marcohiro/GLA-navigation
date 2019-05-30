package Vue;

import java.util.ArrayList;

import ModeleGPS.Utilisateur;
import ModeleGPS.Ville;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import xml.handler;
import xml.xmlEntree;

public class Trajet {

	static handler hand = new handler();
	static xmlEntree xml = new xmlEntree();
	static Ville villeArr, villeDep;

	public static Scene createScene(Stage stage, Utilisateur user) {
		xml.exec(hand);
		// Menu trajet
		Pane trajetPane = new Pane();
		Label menuTrajet = new Label("trajet");
		menuTrajet.setLayoutX(400);
		Label villeD = new Label("Saisir la ville de départ :");
		villeD.setLayoutX(50);
		villeD.setLayoutY(100);
		final ComboBox<String> comboBoxD = new ComboBox<>();
		comboBoxD.setItems(FXCollections.observableArrayList(hand.getNameVilles()));
		comboBoxD.setEditable(true);
		comboBoxD.setLayoutX(180);
		comboBoxD.setLayoutY(100);
		Label villeA = new Label("Saisir la ville d'arrivee :");
		villeA.setLayoutX(50);
		villeA.setLayoutY(200);
		final ComboBox<String> comboBoxA = new ComboBox<>();
		comboBoxA.setItems(FXCollections.observableArrayList(hand.getNameVilles()));
		comboBoxA.setEditable(true);
		comboBoxA.setLayoutX(180);
		comboBoxA.setLayoutY(200);
		Button suivant = new Button("suivant");
		suivant.setLayoutX(250);
		suivant.setLayoutY(350);
		Button retour = new Button("retour");
		retour.setLayoutX(450);
		retour.setLayoutY(350);
		trajetPane.getChildren().addAll(menuTrajet, villeD, comboBoxD, villeA, comboBoxA, suivant, retour);
		suivant.setOnAction(c -> {
			if (comboBoxA.getValue() == null) {
				manqueArrivee();
			} else if (comboBoxD.getValue() == null) {
				manqueDepart();
			} else {
				if (comboBoxA.getValue() == comboBoxD.getValue()) {
					arriveeEgalDepart();
				} else {
					ArrayList<Ville> villes = hand.getVilles();
					for(int i = 0; i<villes.size(); i++) {
						if(villes.get(i).getNom() == comboBoxA.getValue())villeArr = villes.get(i);
						else if(villes.get(i).getNom() == comboBoxD.getValue())villeDep = villes.get(i);
					}
					stage.setScene(ParametresVue.createScene(stage, user, villeDep, villeArr));
				}
			}
		});
		retour.setOnAction(c ->{
			stage.setScene(Scene1.createScene(stage, user));
		});
		return new Scene(trajetPane, 800, 400);
	}
	
	// Alerte : cas : ville de départ = ville d'arrivée
	private static void arriveeEgalDepart() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Le trajet n'a pas été lancé");
		alert.setHeaderText("Le trajet a échouée : ");
		alert.setContentText("Ville de départ et d'arrivée identiques");
		alert.showAndWait();
	}

	// Alerte : cas : ville de départ n'est pas renseignée
	private static void manqueDepart() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Le trajet n'a pas été lancé");
		alert.setHeaderText("Le trajet a échouée : ");
		alert.setContentText("Ville de départ manquante");
		alert.showAndWait();
	}

	// Alerte : cas : ville d'arrivée n'est pas renseignée
	private static void manqueArrivee() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Le trajet n'a pas été lancé");
		alert.setHeaderText("Le trajet a échouée : ");
		alert.setContentText("Ville d'arrivée manquante");
		alert.showAndWait();
	}
	
}
