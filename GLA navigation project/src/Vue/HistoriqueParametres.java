package Vue;

import ModeleGPS.Historique;
import ModeleGPS.Utilisateur;
import ModeleGPS.Parametres;
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

public class HistoriqueParametres {

	static TableView<Parametres> viewParametres;
	static Utilisateur user = new Utilisateur("offline", "offline", null, new Historique());

	public static Scene createScene(Stage stage, Utilisateur user2) {
		// Parametres
		Pane listeDeParametres = new Pane();
		Button retour4 = new Button("retour");

		// Colonne des prix Max
		TableColumn<Parametres, Integer> prixMax = new TableColumn<>("Prix");
		prixMax.setMinWidth(200);
		prixMax.setCellValueFactory(new PropertyValueFactory<>("prixMax"));

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
		viewParametres.setItems(getParametres(user));
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
				user.getHistorique().supprimerParametres(viewParametres.getItems().get(index));
				parametresChoisi.forEach(tousParametres::remove);
			}
		});

		retour4.setOnAction(c -> {
			stage.setScene(HistoriqueVue.createScene(stage, user));
		});

		hboxP.getChildren().addAll(supprP, retour4);
		VBox vbP = new VBox();
		vbP.getChildren().addAll(viewParametres, hboxP);
		listeDeParametres.getChildren().addAll(vbP);
		return new Scene(listeDeParametres, 800, 400);

	}

	// On récupère la liste des parametres
	public static ObservableList<Parametres> getParametres(Utilisateur user) {
		if (user.getHistorique() == null) {

		} else {
			ObservableList<Parametres> parametres = FXCollections.observableArrayList();
			for (int i = 0; i < user.getHistorique().getParametres().size(); i++) {
				parametres.add(user.getHistorique().getParametres().get(i));
			}
			return parametres;
		}
		return null;
	}

}
