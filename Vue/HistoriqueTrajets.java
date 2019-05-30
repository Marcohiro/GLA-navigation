package Vue;

import ModeleGPS.Historique;
import ModeleGPS.Utilisateur;
import ModeleGPS.Trajet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HistoriqueTrajets {

	static TableView<Trajet> viewTrajets;
	static Utilisateur user = new Utilisateur("offline", "offline", null, new Historique());

	public static Scene createScene(Stage stage, Utilisateur user2) {

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

		// Date

		// La table
		viewTrajets = new TableView<>();
		viewTrajets.setItems(getTrajet(user));
		viewTrajets.getColumns().addAll(villeDepart, VilleArrivee, distance, temps);
		viewTrajets.setMaxHeight(350);
		viewTrajets.setMaxWidth(800);
		HBox hboxT = new HBox();
		hboxT.setPadding(new Insets(10, 10, 10, 10));
		hboxT.setSpacing(30);

		retour5.setOnAction(c -> {
			stage.setScene(HistoriqueVue.createScene(stage, user));
		});

		// Supprime un trajet
		Button supprT = new Button("supprimer");

		// repete un trajet
		Button repeter = new Button("repeter");

		hboxT.getChildren().addAll(supprT, repeter, retour5);
		VBox vbT = new VBox();
		vbT.getChildren().addAll(viewTrajets, hboxT);
		listeDeTrajets.getChildren().addAll(vbT);
		return new Scene(listeDeTrajets, 800, 400);
	}

	// On récupère la liste des Trajets
	public static ObservableList<Trajet> getTrajet(Utilisateur user) {
		if (user.getHistorique() == null) {

		} else {
			ObservableList<Trajet> trajets = FXCollections.observableArrayList();
			for (int i = 0; i < user.getHistorique().getTrajets().size(); i++) {
				trajets.add(user.getHistorique().getTrajets().get(i));
			}
			return trajets;
		}
		return null;
	}
}
