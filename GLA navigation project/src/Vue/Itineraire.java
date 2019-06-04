package Vue;

import java.util.ArrayList;
import ModeleGPS.Historique;
import ModeleGPS.Parametres;
import ModeleGPS.Utilisateur;
import ModeleGPS.Vehicule;
import ModeleGPS.Ville;
import ModeleGPS.fileManager;
import ModeleGPS.Trajet;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import xml.handler;
import xml.xmlEntree;

public class Itineraire {

	static TableView<Vehicule> viewVehicules;
	//static Utilisateur user = new Utilisateur("offline", "pass", null, new Historique());
	static fileManager file = new fileManager();
	static handler hand = new handler();
	static xmlEntree xml = new xmlEntree();
	static Ville villeArr, villeDep;
	static Trajet t;

	public static Scene createScene(Stage stage, Utilisateur user, Ville villeDep, Ville villeArr) {
		t = new Trajet(0, "", 0, villeArr, villeArr, user.getHistorique());
		Pane itinéraire = new Pane();
		TextArea chemin = new TextArea();
		Button fin = new Button("fin");
		fin.setLayoutX(400);
		fin.setLayoutY(350);
		ArrayList<Ville> inter = xml.read(villeDep, villeArr, hand);
		xml.exec(hand);
		ArrayList<Ville> villes = hand.getVilles();
		String txt = "";
		for(int i = 0;i<inter.size(); i++) {
			txt +="Etape " + i + " : " + inter.get(i).getNom() + "\n";
		}
		if (inter.size() == 0) {
			txt = "Ville arrivee non atteignable !! ";
		}
		chemin.setText(txt);
		fin.setOnAction(e->{
			user.getHistorique().ajouterTrajet(t);
			//System.out.println(user.getHistorique().getTrajets().get(0).getvilleA());
			file.saveHistorique(user.getPseudo(), user.getHistorique());
			System.out.println("Historique is saved !!!");
			stage.setScene(Scene1.createScene(stage, user));
		});
		itinéraire.getChildren().addAll(chemin, fin);
		return new Scene(itinéraire, 800, 400);
	}
	
}
