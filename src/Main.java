import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

	Stage window;
	Scene login, scene1, trajet, vehicules, historique, historiqueVehicules, historiqueTrajets, historiqueParametres,
			creationCompte;
	TableView<Vehicule> viewVehicules;
	TableView<Trajet> viewTrajets;
	TableView<Parametres> viewParametres;
	Utilisateur user = new Utilisateur("offline", "offline", null, new Historique());
	fileManager file = new fileManager();

	@SuppressWarnings("unchecked")
	public void start(Stage primaryScene) throws Exception {
		// Entree du programme
		window = primaryScene;

		// Bouttons qui gèrent les retours
		Button retour = new Button("retour");
		retour.setOnAction(e -> window.setScene(scene1));
		Button retour1 = new Button("retour");
		retour1.setOnAction(e -> window.setScene(scene1));
		Button retour2 = new Button("retour");
		retour2.setOnAction(e -> window.setScene(scene1));

		// Interface entree du programme
		Button buttonTrajet = new Button("Trajet");
		buttonTrajet.setOnAction(e -> window.setScene(trajet));

		Button consulterHistorique = new Button("Consulter ou modifier historique");
		consulterHistorique.setOnAction(e -> window.setScene(historique));

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
					this.user = new Utilisateur(saisirId.getText(), saisirMDP.getText(), null, new Historique());
					succesConnection();
					window.setScene(scene1);
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
			window.setScene(scene1);
		});
		// Creation de compte
		creerCompte.setOnAction(c -> {
			window.setScene(creationCompte);
		});
		connection.getChildren().addAll(seConnecter, bvn, id, mdp, saisirId, saisirMDP, mdpPerdu, offline, creerCompte);
		login = new Scene(connection, 800, 400);

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
		ObservableList<String> optionsAdresse = FXCollections.observableArrayList();
		final ComboBox<String> comboBoxAdresse = new ComboBox<String>(optionsAdresse);
		comboBoxAdresse.setEditable(true);
		comboBoxAdresse.setLayoutX(300);
		comboBoxAdresse.setLayoutY(200);
		Button creer = new Button("creer le compte");
		creer.setLayoutX(200);
		creer.setLayoutY(350);
		Button retour6 = new Button("retour");
		retour6.setLayoutX(400);
		retour6.setLayoutY(350);
		creer.setOnAction(c -> {
			if (nouvelId.getText() == "")
				identifiantManquant();
			if (!nouveauMDP.getText().equals(confirmationNouveauMDP.getText()))
				erreurMDP();
			// else if(comboBoxAdresse.getValue() == null) adresseManquante();
			else {
				user.setPseudo(nouvelId.getText());
				user.setPassword(nouveauMDP.getText());
				user.setHistorique(new Historique());
				// user.setAdresse(comboBoxAdresse.getValue());
				// Dans le cas ou le nom de l'utilisateur n'existe pas déjà dans la base de
				// données
				if (!file.utilisateurExistant(nouvelId.getText())) {
					file.saveAccount(nouvelId.getText(), nouveauMDP.getText());
					succesCreationCompte();
					nouvelId.clear();
					nouveauMDP.clear();
					window.setScene(scene1);
					// Sinon
				} else {
					compteExistant();
					window.setScene(creationCompte);
				}
			}
		});
		retour6.setOnAction(c -> {
			window.setScene(login);
		});
		creationDeCompte.getChildren().addAll(ident, motDePasse, confirmation, creer, retour6, adresse, creation,
				nouvelId, nouveauMDP, confirmationNouveauMDP, comboBoxAdresse);
		creationCompte = new Scene(creationDeCompte, 800, 400);

		// Menu Principal
		Label menuPrincipal = new Label("Menu principal");
		menuPrincipal.setLayoutX(350);
		Pane main = new Pane();
		buttonTrajet.setLayoutX(50);
		buttonTrajet.setLayoutY(200);
		consulterHistorique.setLayoutX(500);
		consulterHistorique.setLayoutY(200);
		main.getChildren().addAll(menuPrincipal, buttonTrajet, consulterHistorique);
		scene1 = new Scene(main, 800, 400);

		// Menu trajet
		Pane trajetPane = new Pane();
		Label menuTrajet = new Label("trajet");
		menuTrajet.setLayoutX(400);
		Label villeD = new Label("Saisir la ville de départ :");
		villeD.setLayoutX(50);
		villeD.setLayoutY(100);
		ObservableList<String> optionsD = FXCollections.observableArrayList();
		final ComboBox<String> comboBoxD = new ComboBox<String>(optionsD);
		comboBoxD.setEditable(true);
		comboBoxD.setLayoutX(180);
		comboBoxD.setLayoutY(100);
		Label villeA = new Label("Saisir la ville d'arrivee :");
		villeA.setLayoutX(400);
		villeA.setLayoutY(100);
		ObservableList<String> optionsA = FXCollections.observableArrayList();
		final ComboBox<String> comboBoxA = new ComboBox<String>(optionsA);
		comboBoxA.setEditable(true);
		comboBoxA.setLayoutX(525);
		comboBoxA.setLayoutY(100);
		Button suivant = new Button("suivant");
		suivant.setLayoutX(250);
		suivant.setLayoutY(350);
		retour1.setLayoutX(450);
		retour1.setLayoutY(350);
		trajetPane.getChildren().addAll(menuTrajet, villeD, comboBoxD, villeA, comboBoxA, suivant, retour1);
		trajet = new Scene(trajetPane, 800, 400);

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
		retour.setLayoutX(350);
		retour.setLayoutY(350);
		listeVehicules.setOnAction(b -> {
			window.setScene(historiqueVehicules);
		});
		listeParametres.setOnAction(b -> {
			window.setScene(historiqueParametres);
		});
		listeTrajets.setOnAction(b -> {
			window.setScene(historiqueTrajets);
		});
		menuHistorique.getChildren().addAll(listeVehicules, listeParametres, listeTrajets, retour);
		historique = new Scene(menuHistorique, 800, 400);

		// Vehicules
		Pane listeDeVehicules = new Pane();
		Button retour3 = new Button("retour");

		// Colonne des noms
		TableColumn<Vehicule, String> nomV = new TableColumn<>("Nom");
		nomV.setMinWidth(300);
		nomV.setCellValueFactory(new PropertyValueFactory<>("name"));

		// Colonne des gabarit
		TableColumn<Vehicule, Gabarit> gabV = new TableColumn<>("Gabarit");
		gabV.setMinWidth(250);
		gabV.setCellValueFactory(new PropertyValueFactory<>("gabarit"));

		// Colonne du carburant
		TableColumn<Vehicule, Carburant> carV = new TableColumn<>("Carburant");
		carV.setMinWidth(240);
		carV.setCellValueFactory(new PropertyValueFactory<>("carburant"));

		// Nom Vehicule
		TextField nomVehicule = new TextField();

		// Carburant
		final ComboBox<Carburant> comboBox = new ComboBox<>();
		comboBox.setItems(FXCollections.observableArrayList(Carburant.values()));

		// Gabarit
		final ComboBox<Gabarit> comboBoxG = new ComboBox<>();
		comboBoxG.setItems(FXCollections.observableArrayList(Gabarit.values()));

		Button ajout = new Button("ajout");
		Button suppr = new Button("supprimer");
		Button modifier = new Button("modifier");

		// La table
		viewVehicules = new TableView<>();
		viewVehicules.setItems(getVehicules());
		viewVehicules.getColumns().addAll(nomV, gabV, carV);
		viewVehicules.setMaxHeight(350);
		viewVehicules.setMaxWidth(800);
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(10, 10, 10, 10));
		hbox.setSpacing(30);

		retour3.setOnAction(c -> {
			window.setScene(historique);
		});

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
				System.out.println(nomVehicule.getText());
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
		// Supprime les véhicules choisis
		suppr.setOnAction(a -> {
			ObservableList<Vehicule> vehiculeChoisi, tousVehicule;
			tousVehicule = viewVehicules.getItems();
			vehiculeChoisi = viewVehicules.getSelectionModel().getSelectedItems();
			int index = viewVehicules.getSelectionModel().getSelectedIndex();
			this.user.getHistorique().supprimerVehicule(viewVehicules.getItems().get(index));
			vehiculeChoisi.forEach(tousVehicule::remove);
		});
		hbox.getChildren().addAll(nomVehicule, comboBox, comboBoxG, ajout, modifier, suppr, retour3);
		VBox vb = new VBox();
		vb.getChildren().addAll(viewVehicules, hbox);
		listeDeVehicules.getChildren().addAll(vb);
		historiqueVehicules = new Scene(listeDeVehicules, 800, 400);

		// Parametres
		Pane listeDeParametres = new Pane();
		Button retour4 = new Button("retour");

		// Colonne des prix Max
		TableColumn<Parametres, Integer> prixMax = new TableColumn<>("Prix");
		nomV.setMinWidth(200);
		nomV.setCellValueFactory(new PropertyValueFactory<>("prixMax"));

		// Colonne du volume de CO2 Max
		TableColumn<Parametres, Integer> volumeCO2Max = new TableColumn<>("VolumeCO2");
		volumeCO2Max.setMinWidth(200);
		volumeCO2Max.setCellValueFactory(new PropertyValueFactory<>("volumeCO"));

		// Colonne trajet plus court?
		TableColumn<Parametres, Boolean> distancePlusCourte = new TableColumn<>("Plus court");
		distancePlusCourte.setMinWidth(200);
		distancePlusCourte.setCellValueFactory(new PropertyValueFactory<>("distancePlusCourte"));

		// Colonne plus rapide?
		TableColumn<Parametres, Boolean> rapideDuree = new TableColumn<>("Plus rapide");
		rapideDuree.setMinWidth(200);
		rapideDuree.setCellValueFactory(new PropertyValueFactory<>("rapideDuree"));

		Button supprP = new Button("supprimer");
		
		// La table
		viewParametres = new TableView<>();
		viewParametres.setItems(getParametres());
		viewParametres.getColumns().addAll(prixMax, volumeCO2Max, distancePlusCourte, rapideDuree);
		viewParametres.setMaxHeight(350);
		viewParametres.setMaxWidth(800);
		HBox hboxP = new HBox();
		hboxP.setPadding(new Insets(10, 10, 10, 10));
		hboxP.setSpacing(30);

		// Supprime les parametres choisis
		supprP.setOnAction(a -> {
			if (user.getHistorique().getParametres().size() > 0) {
				ObservableList<Parametres> parametresChoisi, tousParametres;
				tousParametres = viewParametres.getItems();
				parametresChoisi = viewParametres.getSelectionModel().getSelectedItems();
				int index = viewParametres.getSelectionModel().getSelectedIndex();
				this.user.getHistorique().supprimerVehicule(viewVehicules.getItems().get(index));
				parametresChoisi.forEach(tousParametres::remove);
			}
		});

		retour4.setOnAction(c -> {
			window.setScene(historique);
		});
		
		hboxP.getChildren().addAll(supprP, retour4);
		VBox vbP = new VBox();
		vbP.getChildren().addAll(viewParametres, hboxP);
		listeDeParametres.getChildren().addAll(vbP);
		historiqueParametres = new Scene(listeDeParametres, 800, 400);

		// Trajets
		Pane listeDeTrajets = new Pane();
		Button retour5 = new Button("retour");
		// Colonne des ville de Depart
		TableColumn<Trajet, String> villeDepart = new TableColumn<>("Ville de depart");
		villeDepart.setMinWidth(200);
		villeDepart.setCellValueFactory(new PropertyValueFactory<>("villeDepart"));

		// Colonne des villes d'arrivee
		TableColumn<Trajet, String> VilleArrivee = new TableColumn<>("Ville d'arrivee");
		VilleArrivee.setMinWidth(200);
		VilleArrivee.setCellValueFactory(new PropertyValueFactory<>("villeArrivee"));

		// Colonne des distances
		TableColumn<Trajet, Integer> distance = new TableColumn<>("Distance");
		distance.setMinWidth(200);
		distance.setCellValueFactory(new PropertyValueFactory<>("distance"));

		// Colonne temps
		TableColumn<Trajet, Integer> temps = new TableColumn<>("Duree");
		temps.setMinWidth(200);
		temps.setCellValueFactory(new PropertyValueFactory<>("duree"));

		//Date
		
		// La table
		viewTrajets = new TableView<>();
		viewTrajets.setItems(getTrajet());
		viewTrajets.getColumns().addAll(villeDepart, VilleArrivee, distance, temps);
		viewTrajets.setMaxHeight(350);
		viewTrajets.setMaxWidth(800);
		HBox hboxT = new HBox();
		hboxT.setPadding(new Insets(10, 10, 10, 10));
		hboxT.setSpacing(30);
		
		retour5.setOnAction(c -> {
			window.setScene(historique);
		});
		
		//Supprime un trajet
		Button supprT = new Button("supprimer");
		
		//repete un trajet
		Button repeter = new Button("repeter");
		
		hboxT.getChildren().addAll(supprT, repeter, retour5);
		VBox vbT = new VBox();
		vbT.getChildren().addAll(viewTrajets, hboxT);
		listeDeTrajets.getChildren().addAll(vbT);
		historiqueTrajets = new Scene(listeDeTrajets, 800, 400);

		window.setScene(login);
		window.setTitle("NAVI");
		// Pour empêcher la modification de la taille de la fenetre de l'application
		window.setResizable(false);
		window.show();
	}

	public static void main(String args[]) {
		launch(args);
	}

	// On récupère la liste des vehicules
	public ObservableList<Vehicule> getVehicules() {
		ObservableList<Vehicule> vehicules = FXCollections.observableArrayList();
		for (int i = 0; i < user.getHistorique().getVehicules().size(); i++) {
			vehicules.add(user.getHistorique().getVehicules().get(i));
		}
		return vehicules;
	}

	// On récupère la liste des parametres
	public ObservableList<Parametres> getParametres() {
		ObservableList<Parametres> parametres = FXCollections.observableArrayList();
		for (int i = 0; i < user.getHistorique().getParametres().size(); i++) {
			parametres.add(user.getHistorique().getParametres().get(i));
		}
		return parametres;
	}

	// On récupère la liste des Trajets
	public ObservableList<Trajet> getTrajet() {
		ObservableList<Trajet> trajets = FXCollections.observableArrayList();
		for (int i = 0; i < user.getHistorique().getTrajets().size(); i++) {
			trajets.add(user.getHistorique().getTrajets().get(i));
		}
		return trajets;
	}

	// Alerte : cas : Le nom du véhicule n'est pas renseigne
	private void alerteNomVehiculeNull() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Pas de vehicule saisit");
		alert.setHeaderText("Resultat : ");
		alert.setContentText("Veuillez saisir un nom de vehicule!");
		alert.showAndWait();
	}

	// Alerte : cas : Le gabarit du véhicule n'est pas renseigne
	private void alerteGabaritNull() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Pas de vehicule saisit");
		alert.setHeaderText("Resultat : ");
		alert.setContentText("Veuillez renseigner le gabarit!");
		alert.showAndWait();
	}

	// Alerte : cas : Le type de carburant du véhicule n'est pas renseigne
	private void alerteCarburantNull() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Pas de vehicule saisit");
		alert.setHeaderText("Resultat : ");
		alert.setContentText("Veuillez renseigner le type de carburant!");
		alert.showAndWait();
	}

	// Alerte : cas : Le vehicule saisit existe deja dans la base : N'est pas
	// renseigne
	private void alerteVehiculeDejaExistant() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Pas de vehicule saisit");
		alert.setHeaderText("Resultat : ");
		alert.setContentText("Le vehicule existe deja dans l'historique : Pas de vehicule cree");
		alert.showAndWait();
	}

	// Alerte : cas : Succes saisie du vehicule
	private void succesVehicule() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Vehicule enregistre");
		alert.setHeaderText("Resultat : ");
		alert.setContentText("Le vehicule est bien enregistre dans l'historique");
		alert.showAndWait();
	}

	// Alerte : cas : L'identifiant n'existe pas
	private void identifiantManquant() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Enregistrement échouée");
		alert.setHeaderText("L'enregistrement a échouée : ");
		alert.setContentText("L'identifiant n'a pas été renseigné");
		alert.showAndWait();
	}

	// Alerte : cas : connection réussie
	private void succesConnection() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Connection réuissie");
		alert.setHeaderText("Connection réuissit : ");
		alert.setContentText("Bienvue " + user.getPseudo());
		alert.showAndWait();
	}

	// Alerte : cas : connection hors ligne
	private void connectionHorsLigne() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Connection hors ligne");
		alert.setHeaderText("Resultat : ");
		alert.setContentText("Vous êtes désormais en mode hors ligne");
		alert.showAndWait();
	}

	// Alerte : cas : Les mots de passe ne correspondent pas
	private void erreurMDP() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Echec");
		alert.setHeaderText("La création de compte a échouée : ");
		alert.setContentText("Les mots de passes ne correspondent pas ou n'ont pas été renseignés");
		alert.showAndWait();
	}

	// Alerte : cas : l'adresse n'est pas renseignée
	private void adresseManquante() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Echec");
		alert.setHeaderText("La création de compte a échouée : ");
		alert.setContentText("L'adresse n'a pas été renseignée");
		alert.showAndWait();
	}

	// Alerte : cas : le compte est bien crée
	private void succesCreationCompte() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Reuissite");
		alert.setHeaderText("La création de compte a réussit : ");
		alert.setContentText("Bienvenue " + user.getPseudo());
		alert.showAndWait();
	}

	// Alerte : cas : Le compte existe deja
	private void compteExistant() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Echec");
		alert.setHeaderText("La création de compte a échouée : ");
		alert.setContentText("Le nom de compte a déjà été crée");
		alert.showAndWait();
	}

	// Alerte : cas : Le mot de passe est erronne
	private void mauvaisMDP() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Connection échouée");
		alert.setHeaderText("La connection a échouée : ");
		alert.setContentText("Le mot de passe est éronné ou n'a pas été saisit");
		alert.showAndWait();
	}

	// Alerte : cas : L'identifiant n'existe pas
	private void identifiantInexistant() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Connection échouée");
		alert.setHeaderText("La connection a échouée : ");
		alert.setContentText("L'identifiant n'existe pas ou n'a pas été renseigné");
		alert.showAndWait();
	}

}
