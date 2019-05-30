package Vue;

import ModeleGPS.Historique;
import ModeleGPS.Utilisateur;
import ModeleGPS.fileManager;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Login {

	static fileManager file = new fileManager();
	static Utilisateur user = new Utilisateur("offline", "offline", null, new Historique());

	public static Scene create(Stage stage) {

		// Ecran de connection
		Pane connection = new Pane();
		Label bvn = new Label("Bienvenue dans le navi GLA!");
		bvn.setLayoutX(350);
		Label id = new Label("Identifiant :");
		id.setLayoutX(250);
		id.setLayoutY(100);
		Label mdp = new Label("Mot de passe :");
		mdp.setLayoutX(250);
		mdp.setLayoutY(200);
		TextField saisirId = new TextField();
		saisirId.setLayoutX(350);
		saisirId.setLayoutY(100);
		// Pour masquer le Mot de passe
		PasswordField saisirMDP = new PasswordField();
		saisirMDP.setLayoutX(350);
		saisirMDP.setLayoutY(200);
		Button seConnecter = new Button("Connection");
		seConnecter.setLayoutX(250);
		seConnecter.setLayoutY(350);
		Button mdpPerdu = new Button("Mot de passe perdu?");
		mdpPerdu.setLayoutX(500);
		mdpPerdu.setLayoutY(350);
		Button offline = new Button("Mode sans connection");
		offline.setLayoutX(350);
		offline.setLayoutY(350);
		Button creerCompte = new Button("Creer un compte");
		creerCompte.setLayoutX(100);
		creerCompte.setLayoutY(350);
		// Connection avec ID et MDP
		seConnecter.setOnAction(c -> {
			if (file.utilisateurExistant(saisirId.getText())) {
				if (file.verifieMDPUtilisateur(saisirId.getText(), saisirMDP.getText())) {
					user = new Utilisateur(saisirId.getText(), saisirMDP.getText(), null, new Historique());
					file.decodeHistorique(user.getPseudo(), user.getHistorique());
					succesConnection();
					stage.setScene(Scene1.createScene(stage, user));
				} else {
					mauvaisMDP();
				}
			} else {
				identifiantInexistant();
			}
		});
		// Sans compte, l'utilisateur reste donc celui par défaut qui est "offline"
		offline.setOnAction(c -> {
			connectionHorsLigne();
			stage.setScene(Scene1.createScene(stage, user));
		});
		// Creation de compte
		creerCompte.setOnAction(c -> {
			stage.setScene(CreationDeCompte.createScene(stage));
		});
		connection.getChildren().addAll(seConnecter, bvn, id, mdp, saisirId, saisirMDP, mdpPerdu, offline, creerCompte);
		return new Scene(connection, 800, 400);
	}
	
	// Alerte : cas : L'identifiant n'existe pas
		private static void identifiantInexistant() {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Connection échouée");
			alert.setHeaderText("La connection a échouée : ");
			alert.setContentText("L'identifiant n'existe pas ou n'a pas été renseigné");
			alert.showAndWait();
		}
		
		// Alerte : cas : Le mot de passe est erronne
		private static void mauvaisMDP() {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Connection échouée");
			alert.setHeaderText("La connection a échouée : ");
			alert.setContentText("Le mot de passe est éronné ou n'a pas été saisit");
			alert.showAndWait();
		}
		
		// Alerte : cas : connection réussie
		private static void succesConnection() {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Connection réuissie");
			alert.setHeaderText("Connection réuissit : ");
			alert.setContentText("Bienvue " + user.getPseudo());
			alert.showAndWait();
		}
		
		// Alerte : cas : connection hors ligne
		private static void connectionHorsLigne() {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Connection hors ligne");
			alert.setHeaderText("Resultat : ");
			alert.setContentText("Vous êtes désormais en mode hors ligne");
			alert.showAndWait();
		}
}