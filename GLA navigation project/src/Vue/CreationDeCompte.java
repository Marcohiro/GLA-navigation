package Vue;

import ModeleGPS.Historique;
import ModeleGPS.Utilisateur;
import ModeleGPS.fileManager;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import xml.handler;
import xml.xmlEntree;

public class CreationDeCompte {

	static handler hand = new handler();
	static xmlEntree xml = new xmlEntree();
	static Utilisateur user = new Utilisateur("offline", "offline", null, new Historique());
	static fileManager file = new fileManager();

	public static Scene createScene(Stage stage) {
		xml.exec(hand);
		// Ecran de creation de compte
		Pane creationDeCompte = new Pane();
		Label creation = new Label("Création de votre compte");
		creation.setLayoutX(350);
		Label ident = new Label("Votre identifiant : ");
		ident.setLayoutX(50);
		ident.setLayoutY(50);
		TextField nouvelId = new TextField();
		nouvelId.setLayoutX(300);
		nouvelId.setLayoutY(50);
		Label motDePasse = new Label("Votre mot de passe : ");
		motDePasse.setLayoutX(50);
		motDePasse.setLayoutY(100);
		PasswordField nouveauMDP = new PasswordField();
		nouveauMDP.setLayoutX(300);
		nouveauMDP.setLayoutY(100);
		Label confirmation = new Label("Confirmez votre mot de passe : ");
		confirmation.setLayoutX(50);
		confirmation.setLayoutY(150);
		PasswordField confirmationNouveauMDP = new PasswordField();
		confirmationNouveauMDP.setLayoutX(300);
		confirmationNouveauMDP.setLayoutY(150);
		Label adresse = new Label("Votre adresse : ");
		adresse.setLayoutX(50);
		adresse.setLayoutY(200);
		final ComboBox<String> comboBoxAdresse = new ComboBox<String>();
		comboBoxAdresse.setItems(FXCollections.observableArrayList(hand.getNameVilles()));
		comboBoxAdresse.setEditable(true);
		comboBoxAdresse.setLayoutX(300);
		comboBoxAdresse.setLayoutY(200);
		Button creer = new Button("creer le compte");
		creer.setLayoutX(200);
		creer.setLayoutY(350);
		Button retour = new Button("retour");
		retour.setLayoutX(400);
		retour.setLayoutY(350);
		creer.setOnAction(c -> {
			if (nouvelId.getText() == "")
				identifiantManquant();
			if (!nouveauMDP.getText().equals(confirmationNouveauMDP.getText()))
				erreurMDP();
			else if (comboBoxAdresse.getValue() == null)
				adresseManquante();
			else {
				user.setPseudo(nouvelId.getText());
				user.setPassword(nouveauMDP.getText());
				user.setHistorique(new Historique());
				// user.setAdresse(comboBoxAdresse.getValue());
				// Dans le cas ou le nom de l'utilisateur n'existe pas déjà dans la base de
				// données
				if (!file.utilisateurExistant(nouvelId.getText())) {
					file.saveAccount(nouvelId.getText(), nouveauMDP.getText(), comboBoxAdresse.getValue());
					succesCreationCompte();
					nouvelId.clear();
					nouveauMDP.clear();
					stage.setScene(Scene1.createScene(stage, user));
					// Sinon
				} else {
					compteExistant();
				}
			}
		});
		retour.setOnAction(c -> {
			stage.setScene(Login.create(stage));
		});
		creationDeCompte.getChildren().addAll(ident, motDePasse, confirmation, creer, retour, adresse, creation,
				nouvelId, nouveauMDP, confirmationNouveauMDP, comboBoxAdresse);
		return new Scene(creationDeCompte, 800, 400);

	}
	
	// Alerte : cas : l'adresse n'est pas renseignée
		private static void adresseManquante() {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Echec");
			alert.setHeaderText("La création de compte a échouée : ");
			alert.setContentText("L'adresse n'a pas été renseignée");
			alert.showAndWait();
		}

		
		// Alerte : cas : le compte est bien crée
		private static void succesCreationCompte() {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Reuissite");
			alert.setHeaderText("La création de compte a réussit : ");
			alert.setContentText("Bienvenue " + user.getPseudo());
			alert.showAndWait();
		}
		
		// Alerte : cas : Le compte existe deja
		private static void compteExistant() {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Echec");
			alert.setHeaderText("La création de compte a échouée : ");
			alert.setContentText("Le nom de compte a déjà été crée");
			alert.showAndWait();
		}
		
		// Alerte : cas : L'identifiant n'existe pas
		private static void identifiantManquant() {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Enregistrement échouée");
			alert.setHeaderText("L'enregistrement a échouée : ");
			alert.setContentText("L'identifiant n'a pas été renseigné");
			alert.showAndWait();
		}
		
		// Alerte : cas : Les mots de passe ne correspondent pas
		private static void erreurMDP() {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Echec");
			alert.setHeaderText("La création de compte a échouée : ");
			alert.setContentText("Les mots de passes ne correspondent pas ou n'ont pas été renseignés");
			alert.showAndWait();
		}

}
