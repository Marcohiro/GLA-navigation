package Vue;

import ModeleGPS.Carburant;
import ModeleGPS.Gabarit;
import ModeleGPS.Historique;
import ModeleGPS.Parametres;
import ModeleGPS.Utilisateur;
import ModeleGPS.Vehicule;
import ModeleGPS.Ville;
import ModeleGPS.fileManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import xml.handler;
import xml.xmlEntree;

public class Vehicules {

	static TableView<Vehicule> viewVehicules;
	//static Utilisateur user = new Utilisateur("offline", "pass", null, new Historique());
	static fileManager file = new fileManager();
	static Ville villeArr, villeDep;
	static Parametres p;
	static handler hand = new handler();
	static xmlEntree xml = new xmlEntree();

	public static Scene createScene(Stage stage, Utilisateur user, Ville villeDep, Ville villeArr, Parametres p) {
		// Menu choix du vehicule
		Pane choixVehicule = new Pane();
		Button retour = new Button("retour");

		// Colonne des gabarit
		TableColumn<Vehicule, Gabarit> gabV = new TableColumn<>("Gabarit");
		gabV.setMinWidth(250);
		gabV.setCellValueFactory(new PropertyValueFactory<>("gabarit"));

		// Colonne du carburant
		TableColumn<Vehicule, Carburant> carV = new TableColumn<>("Carburant");
		carV.setMinWidth(240);
		carV.setCellValueFactory(new PropertyValueFactory<>("carburant"));

		// Colonne des noms
		TableColumn<Vehicule, String> nomV = new TableColumn<>("Nom");
		nomV.setMinWidth(300);
		nomV.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("nom"));

		// Nom Vehicule
		TextField nomVehicule = new TextField();

		// Carburant
		final ComboBox<Carburant> comboBox = new ComboBox<>();
		comboBox.setItems(FXCollections.observableArrayList(Carburant.values()));

		// Gabarit
		final ComboBox<Gabarit> comboBoxG = new ComboBox<>();
		comboBoxG.setItems(FXCollections.observableArrayList(Gabarit.values()));
		Button ajout = new Button("ajout");
		Button utiliser = new Button("utiliser");

		// La table
		viewVehicules = new TableView<>();
		viewVehicules.setItems(getVehicules2(user));
		viewVehicules.getColumns().addAll(gabV, carV, nomV);
		viewVehicules.setMaxHeight(350);
		viewVehicules.setMaxWidth(800);
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(10, 10, 10, 10));
		hbox.setSpacing(30);
		hbox.getChildren().addAll(nomVehicule, comboBox, comboBoxG, ajout, utiliser, retour);

		ajout.setOnAction(a -> {
			Vehicule vh = new Vehicule(null, null, "");
			// Si le nom de vehicule n'est pas renseigne
			if (nomVehicule.getText().isEmpty()) {
				alerteNomVehiculeNull();
				// Si le carburant n'est pas renseigne
			} else if (comboBox.getValue() == null) {
				alerteCarburantNull();
				// Si le gabarit n'est pas renseigne
			} else if (comboBoxG.getValue() == null) {
				alerteGabaritNull();
			} else {
				vh = user.saisirVehicule(comboBoxG.getValue(), comboBox.getValue(), nomVehicule.getText());
				if (vh == null) {
					alerteVehiculeDejaExistant();
				} else {
					viewVehicules.getItems().add(vh);
					succesVehicule();
					nomVehicule.clear();
					comboBox.getSelectionModel().clearSelection();
					comboBoxG.getSelectionModel().clearSelection();
				}
			}
		});
		
		utiliser.setOnAction(c->{
			stage.setScene(Itineraire.createScene(stage, user, villeDep, villeArr));
		});
		
		retour.setOnAction(c -> {
			stage.setScene(ParametresVue.createScene(stage, user, villeDep, villeArr));
		});
		VBox vb = new VBox();
		vb.getChildren().addAll(viewVehicules, hbox);
		choixVehicule.getChildren().addAll(vb);
		return new Scene(choixVehicule, 800, 400);
	}

	// On récupère la liste des vehicules
	public static ObservableList<Vehicule> getVehicules2(Utilisateur user) {
		if(user.getHistorique() == null) {
			//Do nothing
			System.out.println("A");
		} else {
		ObservableList<Vehicule> vehicules = FXCollections.observableArrayList();
		for (int i = 0; i < user.getHistorique().getVehicules().size(); i++) {
			vehicules.add(user.getHistorique().getVehicules().get(i));
			System.out.println(i);
			}
		return vehicules;
		}
		return null;
	}
	
	// Alerte : cas : Le nom du véhicule n'est pas renseigne
	private static void alerteNomVehiculeNull() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Pas de vehicule saisit");
		alert.setHeaderText("Resultat : ");
		alert.setContentText("Veuillez saisir un nom de vehicule!");
		alert.showAndWait();
	}

	// Alerte : cas : Le gabarit du véhicule n'est pas renseigne
	private static void alerteGabaritNull() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Pas de vehicule saisit");
		alert.setHeaderText("Resultat : ");
		alert.setContentText("Veuillez renseigner le gabarit!");
		alert.showAndWait();
	}

	// Alerte : cas : Le type de carburant du véhicule n'est pas renseigne
	private static void alerteCarburantNull() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Pas de vehicule saisit");
		alert.setHeaderText("Resultat : ");
		alert.setContentText("Veuillez renseigner le type de carburant!");
		alert.showAndWait();
	}
	
	// Alerte : cas : Le vehicule saisit existe deja dans la base : N'est pas
	// renseigne
	private static void alerteVehiculeDejaExistant() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Pas de vehicule saisit");
		alert.setHeaderText("Resultat : ");
		alert.setContentText("Le vehicule existe deja dans l'historique : Pas de vehicule cree");
		alert.showAndWait();
	}

	// Alerte : cas : Succes saisie du vehicule
	private static void succesVehicule() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Vehicule enregistre");
		alert.setHeaderText("Resultat : ");
		alert.setContentText("Le vehicule est bien enregistre dans l'historique");
		alert.showAndWait();
	
	}
	
}
